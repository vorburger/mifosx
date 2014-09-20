/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.core.boot;

import org.springframework.context.annotation.Import;

/**
 * Configuration for Mifos X on Spring Boot, used by the ServerApplication.
 *
 * This adds the embedded Tomcat, and a DataSource to a "normal" external
 * database. This does Configuration does not use MariaDB4j.
 */
@Import({ DataSourceConfiguration.class,
		EmbeddedTomcatWithSSLConfiguration.class })
public class MifosConfiguration extends AbstractConfiguration {
}