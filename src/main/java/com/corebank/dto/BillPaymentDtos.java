package com.corebank.dto;

import java.math.BigDecimal;

public class BillPaymentDtos {
    public static class CreatePayeeRequest {
        public String name;
        public String accountNumber;
        public String reference;
    }
    public static class CreateBillPaymentRequest {
        public Long sourceAccountId;
        public Long payeeId;
        public BigDecimal amount;
        public String type; // ONE_TIME or SCHEDULED
        public String scheduledFor; // ISO-8601; optional
        public String scheduleRule; // optional
    }
}
