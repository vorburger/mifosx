/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.jobs.annotation.CronTarget;
import org.mifosplatform.infrastructure.jobs.exception.JobExecutionException;
import org.mifosplatform.infrastructure.jobs.service.JobName;
import org.mifosplatform.infrastructure.sms.domain.SmsMessage;
import org.mifosplatform.infrastructure.sms.domain.SmsMessageRepository;
import org.mifosplatform.infrastructure.sms.domain.SmsMessageStatusType;
import org.mifosplatform.infrastructure.sms.gateways.SmsGateway;
import org.mifosplatform.infrastructure.sms.gateways.SmsGatewayFactory;
import org.mifosplatform.infrastructure.sms.gateways.SmsGatewayFactoryFromDBImpl;
import org.mifosplatform.infrastructure.sms.gateways.SmsGatewayMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduledSendSMSJobServiceImpl implements ScheduledSendSMSJobService {

    private final static Logger logger = LoggerFactory.getLogger(ScheduledSendSMSJobServiceImpl.class);
    private static final String LOG1 = "found total of {} pending SMS messages, and delivered {} messages to gateway (per-job limit is {})";

    private final SmsMessageRepository repository;
    private final SmsGatewayFactory gatewayFactory = new SmsGatewayFactoryFromDBImpl();

    @Autowired
    public ScheduledSendSMSJobServiceImpl(SmsMessageRepository repository/*
                                                                          * ,
                                                                          * SmsGatewayFactory
                                                                          * gatewayFactory
                                                                          */) {
        super();
        this.repository = repository;
        // this.gatewayFactory = gatewayFactory;
    }

    @Override
    @Transactional
    @CronTarget(jobName = JobName.POLL_AND_SEND_PENDING_SMS)
    public void pollDBAndSendPendingSMSToGateway() throws JobExecutionException {
        long pendingMsgs = repository.countPending();
        if (pendingMsgs == 0) return;

        SmsGateway gateway = gatewayFactory.getObject();

        int maxMessagesToProcess = getMaxNumberOfMessagesToProcessPerPoll();
        Pageable pageable = new PageRequest(0, maxMessagesToProcess);
        List<SmsMessage> pendingMessages = repository.findPending(pageable);
        int messagesToProcess = pendingMessages.size(); // needed for logging

        List<SmsGatewayMessage> gatewayMessages = new ArrayList<SmsGatewayMessage>(messagesToProcess);
        for (SmsMessage dbMessage : pendingMessages) {
            SmsGatewayMessage gatewayMessage = new SmsGatewayMessage(dbMessage.getId(), dbMessage.getMobileNo(), dbMessage.getMessage());
            gatewayMessages.add(gatewayMessage);
            dbMessage.setStatus(SmsMessageStatusType.DELIVERED);
        }
        gateway.send(gatewayMessages);
        logger.info(LOG1, pendingMsgs, messagesToProcess, maxMessagesToProcess);

        // NOTE we do NOT have do repository.save()
    }

    // @Override
    // @Transactional
    // @CronTarget(jobName = JobName.UPDATE_SMS_DELIVERY_STATUS)
    // public void pollDBAndUpdateDeliveryStatus() throws JobExecutionException
    // {
    // // Find chunks of messages in status SENT,
    // // and check if SmsGateway.getDeliveryStatus() has any news about them
    // // and, if yes, update their resp. status to either DELIVERED or FAILED
    // }

    /**
     * How many messages to process per job invocation poll? Too small value
     * means too slow sending. Too big value means transaction over too many
     * messages, and in the edge case when the lights go out while sending, will
     * wrongly unnecessarily re-send more message out again.
     * 
     * @return number of messages
     */
    public int getMaxNumberOfMessagesToProcessPerPoll() {
        return 100;
    }

}
