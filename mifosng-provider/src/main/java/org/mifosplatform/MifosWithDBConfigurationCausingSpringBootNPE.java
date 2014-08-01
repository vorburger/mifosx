package org.mifosplatform;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MariaDB4jDataSourceConfiguration.class)
public class MifosWithDBConfigurationCausingSpringBootNPE extends MifosConfiguration {
}