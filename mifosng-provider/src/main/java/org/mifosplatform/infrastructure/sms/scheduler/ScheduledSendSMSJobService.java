/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.scheduler;

import org.mifosplatform.infrastructure.jobs.exception.JobExecutionException;

public interface ScheduledSendSMSJobService {

	void pollDBAndSendPendingSMSToGateway() throws JobExecutionException;

//	void pollDBAndUpdateDeliveryStatus() throws JobExecutionException;

}
