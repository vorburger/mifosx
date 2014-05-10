/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.gateways.flsms;

import java.util.List;

import org.mifosplatform.infrastructure.sms.domain.SmsMessageStatusType;
import org.mifosplatform.infrastructure.sms.gateways.SmsGateway;
import org.mifosplatform.infrastructure.sms.gateways.SmsGatewayMessage;

/**
 * Gateway to FrontlineSMS.
 * 
 * @see http://www.frontlinesms.com
 */
public class FrontlineSmsGatewayImpl implements SmsGateway {

	// TODO implement.. https://mifosforge.jira.com/browse/MIFOSX-771, https://github.com/openMF/mifosx/pull/857/files#diff-17 ?
	
	@Override
	public List<Long> send(List<SmsGatewayMessage> msgs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SmsMessageStatusType> getDeliveryStatus(List<Long> gatewayMessageIDs) {
		// TODO Auto-generated method stub
		return null;
	}

}
