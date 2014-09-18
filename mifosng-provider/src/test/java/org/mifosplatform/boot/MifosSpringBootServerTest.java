package org.mifosplatform.boot;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mifosplatform.MifosServerApplication;
import org.mifosplatform.MifosWithDBConfiguration;
import org.mifosplatform.common.RestAssuredFixture;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * This is an integration test for the Spring Boot launch stuff.
 *
 * @see MifosServerApplication
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MifosWithDBConfiguration.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0", "management.port=0" })
public class MifosSpringBootServerTest {

    protected RestAssuredFixture util;

    @Test
    public void hasMifosPlatformStarted() {
	util = new RestAssuredFixture(8443);
        List<Map<String, String>> response = util.httpGet("/users");
        assertThat(response.get(0).get("username"), is("mifos"));
    }

}