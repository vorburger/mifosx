package org.mifosplatform;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfiguration {

	// TODO I'd like this to be able to ALTERNATIVELY be configured to do classic JNDI look-up as well, for non-Spring Boot as-before WAR deployments
	// Does DataSourceProperties (extends PoolProperties) setDataSourceJNDI() allow this?
	
	public static final String CONFIGURATION_PREFIX = "mifos.datasource";

	@Autowired
	private DataSourceProperties properties;

	@Bean
	@ConfigurationProperties(prefix = DataSourceConfiguration.CONFIGURATION_PREFIX)
	public DataSource tenantDataSourceJndi() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource(properties);
		return ds;
	}


}
