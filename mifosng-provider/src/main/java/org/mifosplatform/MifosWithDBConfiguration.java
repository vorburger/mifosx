/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({ WebConfiguration.class, MariaDB4jDataSourceConfiguration.class, TomcatSSLConfiguration.class })
@ImportResource("classpath*:META-INF/spring/appContext.xml")
@EnableAutoConfiguration(exclude={ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
// NOTE: Due to https://github.com/spring-projects/spring-boot/issues/1328, we must repeat above, and cannot just extends MifosConfiguration and "override" only @Import(MariaDB4jDataSourceConfiguration.class) 
public class MifosWithDBConfiguration {
}