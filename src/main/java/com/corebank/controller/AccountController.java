package com.corebank.controller;

import com.corebank.model.Account;
import com.corebank.service.AccountService;
import com.corebank.service.CurrentUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final CurrentUserService currentUser;

    public AccountController(AccountService accountService, CurrentUserService currentUser) {
        this.accountService = accountService;
        this.currentUser = currentUser;
    }

    @GetMapping
    public List<Account> list() {
        return accountService.list(currentUser.get());
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id) {
        return accountService.get(currentUser.get(), id);
    }
}
