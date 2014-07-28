package org.mifosplatform;

import javax.validation.constraints.NotNull;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@ConfigurationProperties(prefix = DataSourceConfiguration.CONFIGURATION_PREFIX)
public class DataSourceProperties extends PoolProperties {

	private volatile @NotNull String jdbcProtocol = "jdbc";
	private volatile @NotNull String jdbcSubprotocol = "mysql";
	private volatile @NotNull String hostname = "localhost";
	private volatile @NotNull int port = 3306;
	private volatile @NotNull String dbName = "mifosplatform-tenants";
			
	public DataSourceProperties() {
		super();
		
		// default to save us from re-specifying this; note that it can still be overridden
		setDriverClassName(com.mysql.jdbc.Driver.class.getName());
		setUsername("root");
		setPassword("mysql");
	}
	
	@Override
	public String getUrl() {
		String url = super.getUrl();
		if (StringUtils.hasText(url)) {
			return url;
		}
		url = jdbcProtocol + ":" + jdbcSubprotocol + "://" + hostname  + ":" + port + "/" + dbName;
		return url;
	}
	
}