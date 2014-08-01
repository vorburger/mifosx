/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfiguration {

	// TODO We'd like this to be able to ALTERNATIVELY be configured to do classic JNDI look-up as well, for non-Spring Boot as-before WAR deployments
	// Does DataSourceProperties (extends PoolProperties) setDataSourceJNDI() allow this?
	// https://github.com/spring-projects/spring-boot/issues/989
	
	public static final String CONFIGURATION_PREFIX = "mifos.datasource";

	@Autowired
	private DataSourceProperties properties;

	@Bean
	@ConfigurationProperties(prefix = DataSourceConfiguration.CONFIGURATION_PREFIX)
	public DataSource tenantDataSourceJndi() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource(getProperties());
		return ds;
	}

	protected PoolConfiguration getProperties() {
		return properties;
	}

}