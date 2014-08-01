package org.mifosplatform;

import org.springframework.boot.SpringApplication;

/**
 * Mifos main() launching embedded HTTP server (using Spring Boot).
 *
 * You can easily launch this via Debug as Java Application in your IDE -
 * without needing command line Gradle stuff, no need to build and deploya WAR,
 * remote attachment etc.
 *
 * It's the old/classic Mifos (non-X) Workspace 2.0 reborn for Mifos X! ;-)
 */
public class MifosServerApplication {

	public static void main(String[] args) throws Exception {
        SpringApplication.run(MifosConfiguration.class, args);
    }

}
