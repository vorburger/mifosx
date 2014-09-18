/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Mifos main() launching embedded HTTP server (using Spring Boot) as well as an
 * embedded database (using MariaDB4j).
 *
 * @see MifosServerApplication
 */
public class MifosWithDBServerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(MifosWithDBConfiguration.class);
        app.setShowBanner(false);
        ConfigurableApplicationContext ctx = app.run(args);
        ServerApplicationExit.waitForKeyPressToCleanlyExit(ctx);
    }

}
