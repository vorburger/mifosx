/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.gateways;

import java.util.List;

import org.mifosplatform.infrastructure.sms.domain.SmsMessageStatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLoggingSmsGatewayImpl implements SmsGateway {
	private final static Logger logger = LoggerFactory.getLogger(SimpleLoggingSmsGatewayImpl.class);

	@Override
	public List<Long> send(List<SmsGatewayMessage> msgs) {
		for (SmsGatewayMessage msg : msgs) {
			logger.info(msg.toString());
		}
		return null;
	}

	@Override
	public List<SmsMessageStatusType> getDeliveryStatus(List<Long> gatewayMessageIDs) {
		// Should never happen, as the Scheduler should never call this method, because we returned null above.
		throw new UnsupportedOperationException();
	}

}
