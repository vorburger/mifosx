/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.sms.gateways;

/**
 * SMS Message concept as seen from the side of an SMS Gateway.
 * 
 * Similar to but not identical to the internal view in SmsData & SmsMessage.
 */
public class SmsGatewayMessage {

    private final Long id;
    private final String mobileNo;
    private final String message;

    /**
     * Constructor.
     * 
     * @param id
     *            Mifos internal technical ID of message which a Gateway MAY use
     *            for further tracking - or which may be completely ignored
     * @param mobileNo
     *            Phone number in international format (starting with country
     *            code) without spaces or brackets etc.
     * @param message
     *            Text of actual message. Does not support any sort templating
     *            (although some gateways may, or won't)
     */
    public SmsGatewayMessage(Long id, String mobileNo, String message) {
        super();
        this.id = id;
        this.mobileNo = mobileNo;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SmsGatewayMessage [id=" + id + ", mobileNo=" + mobileNo + ", message=" + message + "]";
    }

}
