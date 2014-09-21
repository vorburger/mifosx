package org.mifosplatform;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class TomcatSSLConfiguration {

    // http://docs.spring.io/spring-boot/docs/1.1.5.RELEASE/reference/htmlsingle/#howto-enable-multiple-connectors-in-tomcat

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector());
        return tomcat;
    }

    protected Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            File keystore = getKeystore().getFile();
            File truststore = keystore;
            connector.setScheme("https");
            connector.setSecure(true);
            connector.setPort(8443);
            protocol.setSSLEnabled(true);
            protocol.setKeystoreFile(keystore.getAbsolutePath());
            protocol.setKeystorePass(getKeystorePass());
            protocol.setTruststoreFile(truststore.getAbsolutePath());
            protocol.setTruststorePass(getKeystorePass());
            // ? protocol.setKeyAlias("apitester");
            return connector;
        } catch (IOException ex) {
            throw new IllegalStateException("can't access keystore: [" + "keystore" + "] or truststore: [" + "keystore" + "]", ex);
        }
    }

    protected String getKeystorePass() {
        return "openmf";
    }

    protected Resource getKeystore() {
        return new ClassPathResource("keystore.jks");
    }

}