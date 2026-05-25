package com.corebank.controller;

import com.corebank.dto.AuthDtos;
import com.corebank.model.User;
import com.corebank.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthDtos.RegisterRequest req) {
        User u = authService.register(req.username, req.email, req.password);
        return ResponseEntity.ok(u.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDtos.LoginRequest req) {
        String token = authService.login(req.username, req.password);
        return ResponseEntity.ok(new AuthDtos.TokenResponse(token));
    }
}
