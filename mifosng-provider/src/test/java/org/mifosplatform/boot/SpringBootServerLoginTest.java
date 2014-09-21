/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.boot;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mifosplatform.MifosServerApplication;
import org.mifosplatform.common.RestAssuredFixture;

/**
 * This is an integration test for the Spring Boot launch stuff.
 *
 * @see MifosServerApplication
 */
public class SpringBootServerLoginTest extends SpringBootIntegrationTest {

    protected RestAssuredFixture util;

    @Test
    public void hasMifosPlatformStarted() {
	util = new RestAssuredFixture(8443);
        List<Map<String, String>> response = util.httpGet("/users");
        assertThat(response.get(0).get("username"), is("mifos"));
    }

}