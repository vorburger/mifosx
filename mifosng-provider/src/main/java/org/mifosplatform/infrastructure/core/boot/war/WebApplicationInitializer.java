/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.core.boot.war;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Required to still have a working legacy "classic" WAR.
 *
 * @see WebApplicationInitializerConfiguration
 */
public class WebApplicationInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // why not share the Spring Boot Love, so true instead false for showBanner()
        return application.showBanner(true).sources(WebApplicationInitializerConfiguration.class);
    }

}