package com.corebank.dto;

import java.math.BigDecimal;

public class TransactionFilter {
    public String from; // ISO-8601 timestamp
    public String to;
    public String type;
    public BigDecimal min;
    public BigDecimal max;
    public Long accountId;
}
