/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.vorburger.mariadb4j.MariaDB4jService;

@Configuration
public class MariaDB4jDataSourceConfiguration extends DataSourceConfiguration {

	@Bean
	public MariaDB4jSetupService mariaDB4jSetupService() {
		return new MariaDB4jSetupService(getMariaDB4jService().getDB());
	}
	
	@Bean
	public MariaDB4jService getMariaDB4jService() {
		MariaDB4jService bean = new MariaDB4jService();
		return bean;
	}
	
	@Override
	protected PoolConfiguration getProperties() {
		PoolConfiguration p = super.getProperties();
		String tenantDB = mariaDB4jSetupService().getTenantDBName();
		p.setUrl(getMariaDB4jService().getURL(tenantDB ));
		return p;
	}

}