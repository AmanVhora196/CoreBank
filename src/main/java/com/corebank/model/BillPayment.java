package com.corebank.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class BillPayment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Account sourceAccount;

    @ManyToOne(optional = false)
    private Payee payee;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentType type; // ONE_TIME, SCHEDULED

    private Instant scheduledFor; // if ONE_TIME future-dated, or next run for SCHEDULED
    private String scheduleRule; // e.g., CRON or simple "MONTHLY" marker

    public enum PaymentType { ONE_TIME, SCHEDULED }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Account getSourceAccount() { return sourceAccount; }
    public void setSourceAccount(Account sourceAccount) { this.sourceAccount = sourceAccount; }
    public Payee getPayee() { return payee; }
    public void setPayee(Payee payee) { this.payee = payee; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public PaymentType getType() { return type; }
    public void setType(PaymentType type) { this.type = type; }
    public Instant getScheduledFor() { return scheduledFor; }
    public void setScheduledFor(Instant scheduledFor) { this.scheduledFor = scheduledFor; }
    public String getScheduleRule() { return scheduleRule; }
    public void setScheduleRule(String scheduleRule) { this.scheduleRule = scheduleRule; }
}
