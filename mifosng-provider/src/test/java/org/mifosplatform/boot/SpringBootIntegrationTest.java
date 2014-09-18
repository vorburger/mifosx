/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.boot;

import org.junit.runner.RunWith;
import org.mifosplatform.MifosWithDBConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MifosWithDBConfiguration.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0", "management.port=0" })
public abstract class SpringBootIntegrationTest {

	// do NOT put any helper methods here!
	// it's much better to use composition instead of inheritance
	// so write a test util ("fixture") and use it as a field in your test

}