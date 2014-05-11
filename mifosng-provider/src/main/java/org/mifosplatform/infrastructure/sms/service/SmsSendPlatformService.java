/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.service;

import org.mifosplatform.infrastructure.sms.domain.SmsMessage;

/**
 * Mifos internal service to send SMS.
 */
public interface SmsSendPlatformService {

    /**
     * Inserts pending message into database, and asks scheduler to process it
     * as soon as possible. (which is ignored in case the job happens to be
     * already running). This is is typically used from code which sends just
     * one SMS (incl. the current REST API).
     * 
     * @param msg
     *            SMS Message
     * @return ID of message created in database, for future delivery tracking
     */
    Long sendOneSMSImmediately(SmsMessage msg);

    /**
     * Inserts pending message into database, for scheduled job to process it
     * some time when it's convenient. This is typically used as part of some
     * code which will generate many SMS in batch.
     * 
     * @param msg
     *            SMS Message
     * @return ID of message created in database, for future delivery tracking
     */
    Long batchOneSMSToSendSomeTime(SmsMessage msg);

}
