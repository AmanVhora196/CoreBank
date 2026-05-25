package com.corebank.controller;

import com.corebank.dto.TransferRequest;
import com.corebank.model.Account;
import com.corebank.service.AccountService;
import com.corebank.service.CurrentUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final AccountService accountService;
    private final CurrentUserService currentUser;

    public TransferController(AccountService accountService, CurrentUserService currentUser) {
        this.accountService = accountService;
        this.currentUser = currentUser;
    }

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody TransferRequest req) {
        var user = currentUser.get();
        Account from = accountService.get(user, req.fromAccountId);
        Account to = accountService.get(user, req.toAccountId); // intra-user for demo
        accountService.transfer(from, to, req.amount, req.description);
        return ResponseEntity.ok().build();
    }
}
