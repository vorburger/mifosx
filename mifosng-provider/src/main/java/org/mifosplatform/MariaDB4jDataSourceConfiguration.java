/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class })
public class MariaDB4jDataSourceConfiguration extends DataSourceConfiguration {

    @Bean
    public MariaDB4jSetupService mariaDB4jSetUp() {
        return new MariaDB4jSetupService(mariaDB4j().getDB());
    }

    @Bean
    public MariaDB4jSpringService mariaDB4j() {
        MariaDB4jSpringService bean = new MariaDB4jSpringService();
        return bean;
    }

    @Override
    protected PoolConfiguration getProperties() {
        PoolConfiguration p = super.getProperties();
        String tenantDB = mariaDB4jSetUp().getTenantDBName();
        p.setUrl(mariaDB4j().getConfiguration().getURL(tenantDB));
        return p;
    }

}