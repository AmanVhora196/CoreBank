package com.corebank.dto;

public class AuthDtos {
    public static class RegisterRequest {
        public String username;
        public String email;
        public String password;
    }
    public static class LoginRequest {
        public String username;
        public String password;
    }
    public static class TokenResponse {
        public String token;
        public TokenResponse(String token) { this.token = token; }
    }
}
