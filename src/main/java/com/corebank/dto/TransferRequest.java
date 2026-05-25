package com.corebank.dto;

import java.math.BigDecimal;

public class TransferRequest {
    public Long fromAccountId;
    public Long toAccountId;
    public BigDecimal amount;
    public String description;
}
