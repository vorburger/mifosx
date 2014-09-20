/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Required to still have a working legacy "classic" WAR.
 *
 * @see MifosServletWebApplicationInitializerConfiguration
 */
public class MifosSpringBootServletWebApplicationInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.showBanner(false).sources(MifosServletWebApplicationInitializerConfiguration.class);
    }

}