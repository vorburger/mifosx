package org.mifosplatform;

import org.springframework.boot.SpringApplication;

/**
 * Mifos main() launching embedded HTTP server.
 *
 * You can easily launch this debug - without needing command line Gradle stuff remote attachment etc.
 * 
 * It's the old/classic Mifos (non-X) Workspace 2.0 reborn! ;-)
 */
public class MifosServerApplication {

	public static void main(String[] args) throws Exception {
        SpringApplication.run(MifosConfiguration.class, args);
    }
	
}
