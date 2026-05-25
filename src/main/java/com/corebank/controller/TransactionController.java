package com.corebank.controller;

import com.corebank.dto.TransactionFilter;
import com.corebank.model.Account;
import com.corebank.model.Transaction;
import com.corebank.service.AccountService;
import com.corebank.service.CurrentUserService;
import com.corebank.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService txService;
    private final AccountService accountService;
    private final CurrentUserService currentUser;

    public TransactionController(TransactionService txService, AccountService accountService, CurrentUserService currentUser) {
        this.txService = txService;
        this.accountService = accountService;
        this.currentUser = currentUser;
    }

    @GetMapping
    public List<Transaction> search(TransactionFilter f) {
        Account account = accountService.get(currentUser.get(), f.accountId);
        Instant from = f.from != null ? Instant.parse(f.from) : null;
        Instant to = f.to != null ? Instant.parse(f.to) : null;
        BigDecimal min = f.min;
        BigDecimal max = f.max;
        return txService.find(account, from, to, f.type, min, max);
    }
}
