/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.scheduler;

import org.mifosplatform.infrastructure.jobs.annotation.CronTarget;
import org.mifosplatform.infrastructure.jobs.exception.JobExecutionException;
import org.mifosplatform.infrastructure.jobs.service.JobName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduledSendSMSJobServiceImpl implements ScheduledSendSMSJobService {
    private final static Logger logger = LoggerFactory.getLogger(ScheduledSendSMSJobServiceImpl.class);

        @Override
        @Transactional
        @CronTarget(jobName = JobName.POLL_AND_SEND_PENDING_SMS)
        public void pollDBAndSendPendingSMSToGateway() throws JobExecutionException {
                System.out.println("pollDBAndSendPendingSMSToGateway println");
                logger.error("pollDBAndSendPendingSMSToGateway logger.error");
                
                // TODO Implementation...
                // Find chunks of messages in status PENDING,
                //    and push them into SmsGateway.send()
                //    and set their status to SENT
        }
        
//      @Override
//      @Transactional
//      @CronTarget(jobName = JobName.UPDATE_SMS_DELIVERY_STATUS)
//      public void pollDBAndUpdateDeliveryStatus() throws JobExecutionException {
//              // TODO Implementation...
//              // Find chunks of messages in status SENT,
//              //    and check if SmsGateway.getDeliveryStatus() has any news about them
//              //    and, if yes, update their resp. status to either DELIVERED or FAILED
//      }
}
