/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform;

import org.mifosplatform.infrastructure.core.boot.ServerApplicationConfiguration;
import org.mifosplatform.infrastructure.core.boot.ApplicationExitUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Mifos main() launching embedded HTTP server (using Spring Boot).
 *
 * You can easily launch this via Debug as Java Application in your IDE -
 * without needing command line Gradle stuff, no need to build and deploy a WAR,
 * remote attachment etc.
 *
 * It's the old/classic Mifos (non-X) Workspace 2.0 reborn for Mifos X! ;-)
 *
 * @see ServerWithMariaDB4jApplication for a variant which incl. an embedded DB if you prefer
 */
public class ServerApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(ServerApplicationConfiguration.class, args);
        ApplicationExitUtil.waitForKeyPressToCleanlyExit(ctx);
    }

}
