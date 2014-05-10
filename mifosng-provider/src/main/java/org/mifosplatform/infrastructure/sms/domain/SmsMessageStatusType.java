package org.mifosplatform.infrastructure.sms.domain;

public enum SmsMessageStatusType {

    INVALID(0, "smsMessageStatusType.invalid"),       // = ?
    PENDING(100, "smsMessageStatusType.pending"),     // = waiting to be sent to a SmsGateway by the ScheduledSendSMSJobService
    SENT(200, "smsMessageStatusType.sent"),           // = handed over to a SmsGateway by the ScheduledSendSMSJobService for further downstream delivery
    DELIVERED(300, "smsMessageStatusType.delivered"), // = to the best of our knowledge, based on information from SmsGateway and what's behind it, has actually been delivered to intended end recipient
    FAILED(400, "smsMessageStatusType.failed");       // = rejected by (one of possibly several) downstream stations. Retry may or may not work (depends on cause)

    private final Integer value;
    private final String code;

    public static SmsMessageStatusType fromInt(final Integer statusValue) {

        SmsMessageStatusType enumeration = SmsMessageStatusType.INVALID;
        switch (statusValue) {
            case 100:
                enumeration = SmsMessageStatusType.PENDING;
            break;
            case 200:
                enumeration = SmsMessageStatusType.SENT;
            break;
            case 300:
                enumeration = SmsMessageStatusType.DELIVERED;
            break;
            case 400:
                enumeration = SmsMessageStatusType.FAILED;
            break;
        }
        return enumeration;
    }

    private SmsMessageStatusType(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }
}