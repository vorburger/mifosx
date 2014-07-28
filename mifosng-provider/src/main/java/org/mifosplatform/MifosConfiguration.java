package org.mifosplatform;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(DataSourceConfiguration.class)
@ImportResource("classpath*:META-INF/spring/appContext.xml")
@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
public class MifosConfiguration {

}
