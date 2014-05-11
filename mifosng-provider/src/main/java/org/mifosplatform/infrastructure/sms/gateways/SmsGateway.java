/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.gateways;

import java.util.List;

import org.mifosplatform.infrastructure.sms.domain.SmsMessageStatusType;

/**
 * Bridge to some Mifos external system which can take SMS one step further out
 * into (or from) the world.
 * 
 * This is NOT intended to be used by any "functional" code inside Mifos, other
 * than the ScheduledSendSMSJobService. If you want to send an SMS from code
 * running inside Mifos, use the SmsSendPlatformService. (And the REST API
 * sitting on top of that if you are running out-of-process, e.g. from the UI.)
 */
public interface SmsGateway {

    /**
     * Send a chunk of SMS.
     * 
     * Strictly speaking actually more like
     * "hand over a message to a gateway for it to send it, some time".
     * 
     * @param msg
     *            SMS message to send out
     * 
     * @return list of same size as input with gateway specific message IDs,
     *         which COULD but doesn't have to be the Mifos internal message
     *         IDs, for tracking delivery (or null if a gateway implementation
     *         has no such concept)
     */
    List<Long> send(List<SmsGatewayMessage> msgs);

    /**
     * Enquire with the gateway about the delivery status of previously sent
     * messages.
     * 
     * @param gatewayMessageIDs
     *            list of gateway specific IDs obtained in response from send()
     * 
     * @return list of (guaranteed) same size with status set to either
     *         DELIVERED, FAILED or null if the Gateway does not (yet) know
     */
    // TODO re-use SmsMessageStatusType or create another type here? Gateway
    // could return more detailed failure infos.. but what would we do with it
    // outside, other than just logging it in the GatewayImpl??
    List<SmsMessageStatusType> getDeliveryStatus(List<Long> gatewayMessageIDs);

}
