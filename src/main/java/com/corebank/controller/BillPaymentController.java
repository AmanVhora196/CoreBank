package com.corebank.controller;

import com.corebank.dto.BillPaymentDtos;
import com.corebank.model.*;
import com.corebank.repo.*;
import com.corebank.service.BillPaymentService;
import com.corebank.service.CurrentUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api")
public class BillPaymentController {

    private final PayeeRepository payeeRepo;
    private final AccountRepository accountRepo;
    private final BillPaymentRepository billRepo;
    private final BillPaymentService billService;
    private final CurrentUserService currentUser;

    public BillPaymentController(PayeeRepository payeeRepo, AccountRepository accountRepo,
                                 BillPaymentRepository billRepo, BillPaymentService billService,
                                 CurrentUserService currentUser) {
        this.payeeRepo = payeeRepo;
        this.accountRepo = accountRepo;
        this.billRepo = billRepo;
        this.billService = billService;
        this.currentUser = currentUser;
    }

    @PostMapping("/payees")
    public Payee addPayee(@RequestBody BillPaymentDtos.CreatePayeeRequest req) {
        Payee p = new Payee();
        p.setName(req.name);
        p.setAccountNumber(req.accountNumber);
        p.setReference(req.reference);
        p.setOwner(currentUser.get());
        return payeeRepo.save(p);
    }

    @PostMapping("/bill-payments")
    public ResponseEntity<?> createPayment(@RequestBody BillPaymentDtos.CreateBillPaymentRequest req) {
        var user = currentUser.get();
        Account src = accountRepo.findById(req.sourceAccountId).orElseThrow();
        if (!src.getOwner().getId().equals(user.getId())) throw new RuntimeException("Unauthorized");

        Payee py = payeeRepo.findById(req.payeeId).orElseThrow();

        BillPayment bp = new BillPayment();
        bp.setSourceAccount(src);
        bp.setPayee(py);
        bp.setAmount(req.amount);
        bp.setType(BillPayment.PaymentType.valueOf(req.type));
        if (req.scheduledFor != null) bp.setScheduledFor(Instant.parse(req.scheduledFor));
        bp.setScheduleRule(req.scheduleRule);

        bp = billService.create(bp);

        if (bp.getType() == BillPayment.PaymentType.ONE_TIME && (bp.getScheduledFor() == null || bp.getScheduledFor().isBefore(Instant.now()))) {
            billService.executeOneTime(bp);
        }
        return ResponseEntity.ok(bp.getId());
    }
}
