package org.mifosplatform;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(MariaDB4jDataSourceConfiguration.class)
@ImportResource("classpath*:META-INF/spring/appContext.xml")
@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
// NOTE: Due to https://github.com/spring-projects/spring-boot/issues/1328, we must repeat above, and cannot just extends MifosConfiguration and "override" only @Import(MariaDB4jDataSourceConfiguration.class) 
public class MifosWithDBConfiguration {
}