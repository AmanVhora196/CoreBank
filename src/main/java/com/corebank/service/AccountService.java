package com.corebank.service;

import com.corebank.model.Account;
import com.corebank.model.Transaction;
import com.corebank.model.User;
import com.corebank.repo.AccountRepository;
import com.corebank.repo.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository txRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository txRepository) {
        this.accountRepository = accountRepository;
        this.txRepository = txRepository;
    }

    public List<Account> list(User owner) {
        return accountRepository.findByOwner(owner);
    }

    public Account get(User owner, Long id) {
        return accountRepository.findByIdAndOwner(id, owner).orElseThrow();
    }

    @Transactional
    public void transfer(Account from, Account to, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (from.getBalance().compareTo(amount) < 0) throw new IllegalArgumentException("Insufficient funds");

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction debit = new Transaction();
        debit.setAccount(from);
        debit.setType(Transaction.TxType.DEBIT);
        debit.setAmount(amount);
        debit.setDescription(description);
        debit.setTimestamp(Instant.now());
        debit.setCategory("Transfer");
        txRepository.save(debit);

        Transaction credit = new Transaction();
        credit.setAccount(to);
        credit.setType(Transaction.TxType.CREDIT);
        credit.setAmount(amount);
        credit.setDescription(description);
        credit.setTimestamp(Instant.now());
        credit.setCategory("Transfer");
        txRepository.save(credit);
    }
}
