package com.corebank.service;

import com.corebank.model.Budget;
import com.corebank.model.Transaction;
import com.corebank.model.User;
import com.corebank.repo.BudgetRepository;
import com.corebank.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepo;
    private final TransactionRepository txRepo;

    public BudgetService(BudgetRepository budgetRepo, TransactionRepository txRepo) {
        this.budgetRepo = budgetRepo;
        this.txRepo = txRepo;
    }

    public Map<String, Object> insights(User user, List<Transaction> userTx) {
        // Aggregate spend per category in current month
        LocalDate now = LocalDate.now();
        Instant monthStart = now.withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant nextMonthStart = now.plusMonths(1).withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        Map<String, BigDecimal> spent = new HashMap<>();
        userTx.stream()
                .filter(t -> t.getTimestamp().isAfter(monthStart) && t.getTimestamp().isBefore(nextMonthStart))
                .forEach(t -> {
                    String cat = t.getCategory() == null ? "Uncategorized" : t.getCategory();
                    BigDecimal v = spent.getOrDefault(cat, BigDecimal.ZERO);
                    spent.put(cat, v.add(t.getAmount()));
                });

        var budgets = budgetRepo.findByOwner(user);
        Map<String, Object> result = new HashMap<>();
        result.put("spentByCategory", spent);
        result.put("budgets", budgets);
        return result;
    }
}
