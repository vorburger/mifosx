/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.core.boot.db;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;

/**
 * Service which "fixes up" the schemaServerPort in the tenants table of the
 * mifosplatform-tenants schema. It sets it to the actual current port of
 * MariaDB4j, instead of the default 3306 which is hard-coded in the initial
 * set-up SQL script (V1__mifos-platform-shared-tenants.sql).
 *
 * This service is called by the TenantDatabaseUpgradeService
 * "just at the right time" during start-up, that is AFTER the initialization of
 * the mifosplatform-tenants but BEFORE Flyway runs on all tenant schemas.
 *
 * It's difficult to achieve the same goal elsewhere, because we cannot do this
 * e.g. in MariaDB4jSetupService, as that's too early
 * (TenantDatabaseUpgradeService has not yet run), nor e.g. in
 * TomcatJdbcDataSourcePerTenantService because in there we do not have access
 * to and should not depend on MariaDB4j configuration.
 */
@Service
public class TenantDataSourcePortFixService {
	private static final Logger logger = LoggerFactory.getLogger(TenantDataSourcePortFixService.class);

	// required=false is important here, because in
	// WebApplicationInitializerConfiguration for classic WAR there
	// is (intentionally) no MariaDB4j
	private @Autowired(required=false) MariaDB4jSpringService mariaDB4j;

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public TenantDataSourcePortFixService(@Qualifier("tenantDataSourceJndi") final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void fixUpTenantsSchemaServerPort() {
	if (mariaDB4j == null || !mariaDB4j.isRunning()) {
		// we don't have any generic mechanism to know the DB port, given just a tenant DataSource
		logger.info("No schema_server_port UPDATE made to tenants table of the mifosplatform-tenants schema (because MariaDB4j is not used)");
		return;
	}
	int port = mariaDB4j.getConfiguration().getPort();
	int r = jdbcTemplate.update("UPDATE tenants SET schema_server_port = ?", port);
	if ( r == 0 )
		logger.error("UPDATE tenants SET schema_server_port=" + port + " did not update ANY rows - something is probably wrong");
	else
		logger.info("Upated " + r + " rows in the tenants table of the mifosplatform-tenants schema to the real MariaDB4j port: " + port);
    }

}
