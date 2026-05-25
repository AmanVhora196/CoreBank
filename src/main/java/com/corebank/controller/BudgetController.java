package com.corebank.controller;

import com.corebank.model.Transaction;
import com.corebank.repo.TransactionRepository;
import com.corebank.service.BudgetService;
import com.corebank.service.CurrentUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;
    private final TransactionRepository txRepo;
    private final CurrentUserService currentUser;

    public BudgetController(BudgetService budgetService, TransactionRepository txRepo, CurrentUserService currentUser) {
        this.budgetService = budgetService;
        this.txRepo = txRepo;
        this.currentUser = currentUser;
    }

    @GetMapping("/insights")
    public Map<String, Object> insights() {
        // In a full app, fetch only current user's transactions; here we filter via accounts in services.
        List<Transaction> all = txRepo.findAll();
        return budgetService.insights(currentUser.get(), all);
    }
}
