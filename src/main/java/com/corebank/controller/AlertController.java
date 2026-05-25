package com.corebank.controller;

import com.corebank.model.AlertPreference;
import com.corebank.service.AlertService;
import com.corebank.service.CurrentUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertService alertService;
    private final CurrentUserService currentUser;

    public AlertController(AlertService alertService, CurrentUserService currentUser) {
        this.alertService = alertService;
        this.currentUser = currentUser;
    }

    @GetMapping("/prefs")
    public AlertPreference getPrefs() {
        return alertService.getOrCreate(currentUser.get());
    }

    @PutMapping("/prefs")
    public AlertPreference update(@RequestBody AlertPreference ap) {
        ap.setUser(currentUser.get());
        return alertService.save(ap);
    }

    @PostMapping("/evaluate")
    public List<String> evaluate() {
        return alertService.evaluate(currentUser.get());
    }
}
