package com.corebank.service;

import com.corebank.model.*;
import com.corebank.repo.AccountRepository;
import com.corebank.repo.BillPaymentRepository;
import com.corebank.repo.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class BillPaymentService {

    private final BillPaymentRepository billRepo;
    private final AccountRepository accountRepo;
    private final TransactionRepository txRepo;

    public BillPaymentService(BillPaymentRepository billRepo, AccountRepository accountRepo, TransactionRepository txRepo) {
        this.billRepo = billRepo;
        this.accountRepo = accountRepo;
        this.txRepo = txRepo;
    }

    @Transactional
    public BillPayment create(BillPayment bp) {
        return billRepo.save(bp);
    }

    @Transactional
    public void executeOneTime(BillPayment bp) {
        // Simple immediate debit for demo
        var account = bp.getSourceAccount();
        account.setBalance(account.getBalance().subtract(bp.getAmount()));
        accountRepo.save(account);

        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setType(Transaction.TxType.BILL_PAYMENT);
        tx.setAmount(bp.getAmount());
        tx.setDescription("Bill payment to " + bp.getPayee().getName());
        tx.setTimestamp(Instant.now());
        tx.setCategory("Bills");
        txRepo.save(tx);
    }
}
