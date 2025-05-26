package com.example.demo3.controller;

import com.example.demo3.dto.input.LoginRequest;
import com.example.demo3.dto.input.RegisterRequest;
import com.example.demo3.dto.output.JwtResponse;
import com.example.demo3.model.User;
import org.springframework.web.bind.annotation.*;
import com.example.demo3.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth;

    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest user) {
        return auth.register(user);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest login) {
        return auth.login(login);
    }
}
