package com.corebank.repo;

import com.corebank.model.Account;
import com.corebank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountAndTimestampBetween(Account account, Instant from, Instant to);
    List<Transaction> findByAccount(Account account);
}
