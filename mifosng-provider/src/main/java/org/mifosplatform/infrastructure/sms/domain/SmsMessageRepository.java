/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.domain;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SmsMessageRepository extends JpaRepository<SmsMessage, Long>, JpaSpecificationExecutor<SmsMessage> {

    // TODO how to? @Query("from SmsMessage msg where msg.statusType = " +
    // SmsMessageStatusType.PENDING.getValue() + " order by msg.id")
    @Query("from SmsMessage msg where msg.statusType = 100 order by msg.id")
    List<SmsMessage> findPending(Pageable pageable);

}