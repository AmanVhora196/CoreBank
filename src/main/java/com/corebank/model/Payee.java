package com.corebank.model;

import jakarta.persistence.*;

@Entity
public class Payee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String accountNumber;
    private String reference;

    @ManyToOne
    private User owner;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}
