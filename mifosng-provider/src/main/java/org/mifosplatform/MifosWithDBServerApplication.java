/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Mifos main() launching embedded HTTP server (using Spring Boot)
 * as well as an embedded database (using MariaDB4j).
 *
 * @see MifosServerApplication
 */
public class MifosWithDBServerApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(MifosWithDBConfiguration.class, args);

		// NOTE: In Eclipse, the Shutdown Hooks are not invoked on exit (red button)..
		// In the case of MariaDB4j that's a problem because then the mysqld won't be stopped,
		// so: (@see https://bugs.eclipse.org/bugs/show_bug.cgi?id=38016)
		System.out.println("\nHit Enter to quit...");
		// NOTE: In Eclipse, System.console() is not available.. so: (@see https://bugs.eclipse.org/bugs/show_bug.cgi?id=122429)
		BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
		d.readLine();

		ctx.stop();
		ctx.close();
	}

}