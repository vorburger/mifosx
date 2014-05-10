/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.gateways;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.sms.domain.SmsMessageStatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackingLoggingSmsGatewayImpl implements SmsGateway {
	private final static Logger logger = LoggerFactory.getLogger(TrackingLoggingSmsGatewayImpl.class);

	@Override
	public List<Long> send(List<SmsGatewayMessage> msgs) {
		List<Long> ids = new ArrayList<Long>(msgs.size());
		for (SmsGatewayMessage msg : msgs) {
			logger.info(msg.toString());
			ids.add(msg.getId());
		}
		return ids;
	}

	@Override
	public List<SmsMessageStatusType> getDeliveryStatus(List<Long> gatewayMessageIDs) {
		List<SmsMessageStatusType> statusList = new ArrayList<SmsMessageStatusType>(gatewayMessageIDs.size());
		for (@SuppressWarnings("unused") Long gatewayMessageID : gatewayMessageIDs) {
			statusList.add(SmsMessageStatusType.DELIVERED);
		}
		return statusList;
	}

}
