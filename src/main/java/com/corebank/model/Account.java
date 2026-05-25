package com.corebank.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions;

    public enum AccountType { CHECKING, SAVINGS }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public AccountType getType() { return type; }
    public void setType(AccountType type) { this.type = type; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}
