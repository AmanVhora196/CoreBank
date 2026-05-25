package com.corebank.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Account account;

    private Instant timestamp = Instant.now();

    @Enumerated(EnumType.STRING)
    private TxType type; // DEBIT, CREDIT, BILL_PAYMENT

    private BigDecimal amount;
    private String description;
    private String category; // For budgeting: e.g., "Groceries", "Utilities"

    public enum TxType { DEBIT, CREDIT, BILL_PAYMENT }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public TxType getType() { return type; }
    public void setType(TxType type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
