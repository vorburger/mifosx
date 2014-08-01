package org.mifosplatform;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.vorburger.mariadb4j.MariaDB4jService;

@Configuration
public class MariaDB4jDataSourceConfiguration extends DataSourceConfiguration {

	@Bean
	public MariaDB4jService getMariaDB4jService() {
		MariaDB4jService bean = new MariaDB4jService();
		return bean;
	}
	
	@Override
	protected PoolConfiguration getProperties() {
		PoolConfiguration p = super.getProperties();
		p.setUrl(getMariaDB4jService().getURL("test")); // TODO not test but *tenant* ? what 'bout the other one??
		return p;
	}

}