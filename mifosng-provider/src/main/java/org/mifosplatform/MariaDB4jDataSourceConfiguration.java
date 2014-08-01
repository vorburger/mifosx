package org.mifosplatform;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.vorburger.mariadb4j.MariaDB4jService;

@Configuration
public class MariaDB4jDataSourceConfiguration extends DataSourceConfiguration {

	@Bean
	public MariaDB4jMifosSetupService mariaDB4jMifosSetupService() {
		return new MariaDB4jMifosSetupService(getMariaDB4jService().getDB());
	}
	
	@Bean
	public MariaDB4jService getMariaDB4jService() {
		MariaDB4jService bean = new MariaDB4jService();
		return bean;
	}
	
	@Override
	protected PoolConfiguration getProperties() {
		PoolConfiguration p = super.getProperties();
		String tenantDB = mariaDB4jMifosSetupService().getTenantDBName();
		p.setUrl(getMariaDB4jService().getURL(tenantDB ));
		return p;
	}

}