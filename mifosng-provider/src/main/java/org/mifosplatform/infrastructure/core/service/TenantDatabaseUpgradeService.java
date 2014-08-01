/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.core.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.mifosplatform.infrastructure.core.domain.MifosPlatformTenant;
import org.mifosplatform.infrastructure.security.service.TenantDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.googlecode.flyway.core.Flyway;

/**
 * A service that picks up on tenants that are configured to auto-update their
 * specific schema on application startup.
 */
@Service
public class TenantDatabaseUpgradeService {

    private final TenantDetailsService tenantDetailsService;
    protected final DataSource tenantDataSource;

    @Autowired
	public TenantDatabaseUpgradeService(
			final TenantDetailsService detailsService,
			@Qualifier("tenantDataSourceJndi") final DataSource dataSource)
    {
        this.tenantDetailsService = detailsService;
        this.tenantDataSource = dataSource;
    }

    @PostConstruct
    public void upgradeAllTenants() {
	upgradeTenantDB();
        final List<MifosPlatformTenant> tenants = this.tenantDetailsService.findAllTenants();
        for (final MifosPlatformTenant tenant : tenants) {
            if (tenant.isAutoUpdateEnabled()) {
		// similar Fly code is also in MariaDB4jSetupService.upgradeTenantDB()
                final Flyway flyway = new Flyway();
                flyway.setDataSource(tenant.databaseURL(), tenant.getSchemaUsername(), tenant.getSchemaPassword());
                flyway.setLocations("sql/migrations/core_db");
                flyway.setOutOfOrder(true);
                flyway.migrate();
            }
        }
    }

	/**
	 * Initializes, and if required upgrades (using Flyway) the Tenant DB itself.
	 */
	protected void upgradeTenantDB() {
		final Flyway flyway = new Flyway();
		flyway.setDataSource(tenantDataSource);
		flyway.setLocations("sql/migrations/list_db");
		flyway.setOutOfOrder(true);
		flyway.migrate();
	}
}