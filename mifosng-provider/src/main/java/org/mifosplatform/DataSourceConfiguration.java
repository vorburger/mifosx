package org.mifosplatform;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfiguration {

	@Bean
	public DataSource tenantDataSourceJndi() {
		// TODO use tomcat-jdbc instead here!
		DriverManagerDataSource dmds = new DriverManagerDataSource(); // SimpleDriverDataSource ?
		dmds.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
		dmds.setUrl("jdbc:mysql://localhost:3306/mifosplatform-tenants");
		dmds.setUsername("root");
		dmds.setPassword("mysql");
		return dmds;
	}


}
