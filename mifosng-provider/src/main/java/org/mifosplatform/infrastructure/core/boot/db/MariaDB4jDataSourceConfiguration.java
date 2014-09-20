/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.core.boot.db;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.mifosplatform.infrastructure.core.boot.DataSourceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;

@Configuration
public class MariaDB4jDataSourceConfiguration extends DataSourceConfiguration {

	@Override
	public DataSource tenantDataSourceJndi() {
		return super.tenantDataSourceJndi();
	}

    @Bean
    public MariaDB4jSetupService mariaDB4jSetUp() {
        return new MariaDB4jSetupService(mariaDB4j().getDB());
    }

    @Bean
    public MariaDB4jSpringService mariaDB4j() {
        MariaDB4jSpringService mariaDB4jSpringService = new MariaDB4jSpringService();
        mariaDB4jSpringService.setDefaultBaseDir("build/db/bin");
        mariaDB4jSpringService.setDefaultDataDir("build/db/data");
        return mariaDB4jSpringService;
    }

    @Override
    protected PoolConfiguration getProperties() {
        PoolConfiguration p = super.getProperties();
        String tenantDB = mariaDB4jSetUp().getTenantDBName();
        p.setUrl(mariaDB4j().getConfiguration().getURL(tenantDB));
        return p;
    }

}