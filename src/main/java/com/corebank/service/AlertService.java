package com.corebank.service;

import com.corebank.model.*;
import com.corebank.repo.AccountRepository;
import com.corebank.repo.AlertPreferenceRepository;
import com.corebank.repo.BillPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private final AlertPreferenceRepository prefRepo;
    private final AccountRepository accountRepo;
    private final BillPaymentRepository billRepo;

    public AlertService(AlertPreferenceRepository prefRepo, AccountRepository accountRepo, BillPaymentRepository billRepo) {
        this.prefRepo = prefRepo;
        this.accountRepo = accountRepo;
        this.billRepo = billRepo;
    }

    public AlertPreference getOrCreate(User user) {
        return prefRepo.findByUser(user).orElseGet(() -> {
            AlertPreference ap = new AlertPreference();
            ap.setUser(user);
            return prefRepo.save(ap);
        });
    }

    public AlertPreference save(AlertPreference ap) { return prefRepo.save(ap); }

    // Simulate evaluating rules and "dispatching" alerts (returns messages instead of sending)
    public List<String> evaluate(User user) {
        AlertPreference prefs = getOrCreate(user);
        List<String> out = new ArrayList<>();

        if (prefs.isLowBalanceEnabled()) {
            var accounts = accountRepo.findByOwner(user);
            accounts.stream()
                .filter(a -> a.getBalance().compareTo(prefs.getLowBalanceThreshold()) < 0)
                .forEach(a -> out.add("[LOW BALANCE] Account " + a.getAccountNumber() + " is below " + prefs.getLowBalanceThreshold()));
        }

        if (prefs.isBillReminderEnabled()) {
            var bills = billRepo.findByOwner(user);
            bills.stream()
                .filter(bp -> bp.getScheduledFor() != null) // simple reminder
                .forEach(bp -> out.add("[BILL REMINDER] " + bp.getPayee().getName() + " scheduled on " + bp.getScheduledFor()));
        }
        // LargeTx alerts would normally be triggered on tx creation; left as a placeholder.
        return out;
    }
}
