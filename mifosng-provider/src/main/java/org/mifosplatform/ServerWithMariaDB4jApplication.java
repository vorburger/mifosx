/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform;

import org.mifosplatform.infrastructure.core.boot.ApplicationExitUtil;
import org.mifosplatform.infrastructure.core.boot.db.ServerWithMariaDB4jApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Mifos main() launching embedded Tomcat HTTP server (using Spring Boot) as well as an
 * embedded database (using MariaDB4j).
 *
 * @see ServerApplication
 */
public class ServerWithMariaDB4jApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ServerWithMariaDB4jApplicationConfiguration.class);
        // actually why not share the Spring Boot Love, so don't: app.setShowBanner(false);
        ConfigurableApplicationContext ctx = app.run(args);
        ApplicationExitUtil.waitForKeyPressToCleanlyExit(ctx);
    }

}
