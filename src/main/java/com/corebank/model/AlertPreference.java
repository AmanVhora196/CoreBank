package com.corebank.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class AlertPreference {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private boolean lowBalanceEnabled = true;
    private BigDecimal lowBalanceThreshold = new BigDecimal("100.00");

    private boolean largeTxEnabled = true;
    private BigDecimal largeTxThreshold = new BigDecimal("500.00");

    private boolean billReminderEnabled = true;

    private String channel = "EMAIL"; // EMAIL or PUSH (placeholder)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public boolean isLowBalanceEnabled() { return lowBalanceEnabled; }
    public void setLowBalanceEnabled(boolean lowBalanceEnabled) { this.lowBalanceEnabled = lowBalanceEnabled; }
    public BigDecimal getLowBalanceThreshold() { return lowBalanceThreshold; }
    public void setLowBalanceThreshold(BigDecimal lowBalanceThreshold) { this.lowBalanceThreshold = lowBalanceThreshold; }

    public boolean isLargeTxEnabled() { return largeTxEnabled; }
    public void setLargeTxEnabled(boolean largeTxEnabled) { this.largeTxEnabled = largeTxEnabled; }
    public BigDecimal getLargeTxThreshold() { return largeTxThreshold; }
    public void setLargeTxThreshold(BigDecimal largeTxThreshold) { this.largeTxThreshold = largeTxThreshold; }

    public boolean isBillReminderEnabled() { return billReminderEnabled; }
    public void setBillReminderEnabled(boolean billReminderEnabled) { this.billReminderEnabled = billReminderEnabled; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }
}
