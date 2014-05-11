package org.mifosplatform.infrastructure.sms.gateways;

import org.springframework.beans.BeansException;


public class SmsGatewayFactoryFromDBImpl implements SmsGatewayFactory {

    @Override
    public SmsGateway getObject() throws BeansException {
        return new SimpleLoggingSmsGatewayImpl();
    }

}
