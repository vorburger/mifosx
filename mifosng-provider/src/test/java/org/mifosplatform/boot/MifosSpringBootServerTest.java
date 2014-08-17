package org.mifosplatform.boot;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mifosplatform.MifosConfiguration;
import org.mifosplatform.MifosServerApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Preconditions;

/**
 * This is an integration test for the Spring Boot launch stuff.
 *
 * @see MifosServerApplication
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MifosConfiguration.class)
// TODO later switch this to MifosWithDBConfiguration (when its faster)
@WebAppConfiguration
@IntegrationTest({ "server.port=0", "management.port=0" })
public class MifosSpringBootServerTest {

    @Value("${local.server.port}")
    protected int httpPort;

    protected RestTemplate template = new TestRestTemplate();

    @Test
    public void hasMifosPlatformStarted() {
        String response = template.getForObject(getApiUrl("/users"), String.class);
        // TODO In case of e.g. 404 (or other error) response == null..
        // it would be better if an Exception would be thrown?
        assertThat(response, containsString("\"username\": \"mifos\""));
    }

    protected String getApiUrl(String trailingApiUrl) {
        Preconditions.checkArgument(trailingApiUrl.startsWith("/"), "trailingApiUrl must start with slash: " + trailingApiUrl);
        return "http://localhost:" + httpPort + "/mifosng-provider/api/v1" + trailingApiUrl + "?tenantIdentifier=default";
    }
}
