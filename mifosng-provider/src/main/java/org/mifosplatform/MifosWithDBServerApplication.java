package org.mifosplatform;

import org.springframework.boot.SpringApplication;

/**
 * Mifos main() launching embedded HTTP server (using Spring Boot)
 * as well as an embedded database (using MariaDB4j).
 * 
 * @see MifosServerApplication
 */
public class MifosWithDBServerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MifosWithDBConfiguration.class, args);
	}

}