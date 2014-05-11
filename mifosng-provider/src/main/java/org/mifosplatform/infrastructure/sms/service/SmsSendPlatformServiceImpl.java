/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.service;

import org.mifosplatform.infrastructure.jobs.service.JobName;
import org.mifosplatform.infrastructure.jobs.service.JobRegisterService;
import org.mifosplatform.infrastructure.sms.domain.SmsMessage;
import org.mifosplatform.infrastructure.sms.domain.SmsMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SmsSendPlatformServiceImpl implements SmsSendPlatformService {

    // Do NOT synchronously fire off requests using any third party
    // SmsGateway service to send SMS here. That is the function
    // of the ScheduledSendSMSJobService.

    private final SmsMessageRepository messagesRepository;
    private final JobRegisterService jobs;
    
    @Autowired
    public SmsSendPlatformServiceImpl(final SmsMessageRepository repository, final JobRegisterService jobRegisterService) {
        this.messagesRepository = repository;
        this.jobs = jobRegisterService;
    }

    @Override
    @Transactional
    public Long sendOneSMSImmediately(final SmsMessage msg) {
        Long newID = batchOneSMSToSendSomeTime(msg);
        jobs.executeJob(JobName.POLL_AND_SEND_PENDING_SMS);
        return newID;
    }

    @Override
    @Transactional
    public Long batchOneSMSToSendSomeTime(final SmsMessage msg) {
        SmsMessage savedMsg = this.messagesRepository.save(msg);
        return savedMsg.getId();
    }

}
