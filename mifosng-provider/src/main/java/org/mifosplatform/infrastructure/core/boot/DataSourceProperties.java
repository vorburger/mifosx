/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.core.boot;

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

        // default to save us from re-specifying this; note that it can still be
        // overridden
        setDriverClassName(com.mysql.jdbc.Driver.class.getName());

        setUsernameAndPassword();

        setMifosDefaults();
    }

    protected void setUsernameAndPassword() {
        if (getUsername() == null) setUsername("root");
        if (getPassword() == null) setPassword("mysql");
    }

    /**
     * as per (some of..) INSTALL.md and
     * org.mifosplatform.infrastructure.core.service
     * .TomcatJdbcDataSourcePerTenantService
     * .createNewDataSourceFor(MifosPlatformTenant)
     */
    protected void setMifosDefaults() {
        setInitialSize(3);
        // setMaxIdle(6); -- strange, why?
        // setMinIdle(3); -- JavaDoc says default is initialSize.. so shouldn't
        // be needed
        if (getValidationQuery() == null) setValidationQuery("SELECT 1");
        setTestOnBorrow(true);
        setTestOnReturn(true);
        setTestWhileIdle(true);
        setTimeBetweenEvictionRunsMillis(30000);
        setTimeBetweenEvictionRunsMillis(60000);
        setLogAbandoned(true);
        setSuspectTimeout(60);

        setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;org.apache.tomcat.jdbc.pool.interceptor.SlowQueryReport");
    }

    @Override
    public String getUrl() {
        String url = super.getUrl();
        if (StringUtils.hasText(url)) { return url; }
        url = jdbcProtocol + ":" + jdbcSubprotocol + "://" + hostname + ":" + port + "/" + dbName;
        return url;
    }

}