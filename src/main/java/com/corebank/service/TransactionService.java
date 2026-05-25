package com.corebank.service;

import com.corebank.model.Account;
import com.corebank.model.Transaction;
import com.corebank.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public List<Transaction> find(Account account, Instant from, Instant to, String type, BigDecimal min, BigDecimal max) {
        List<Transaction> base = (from != null && to != null)
                ? repo.findByAccountAndTimestampBetween(account, from, to)
                : repo.findByAccount(account);

        return base.stream().filter(t -> {
            boolean ok = true;
            if (type != null) ok &= t.getType().name().equalsIgnoreCase(type);
            if (min != null) ok &= t.getAmount().compareTo(min) >= 0;
            if (max != null) ok &= t.getAmount().compareTo(max) <= 0;
            return ok;
        }).collect(Collectors.toList());
    }
}
