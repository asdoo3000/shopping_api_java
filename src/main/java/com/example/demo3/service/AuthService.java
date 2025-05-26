package com.example.demo3.service;

import com.example.demo3.dto.input.LoginRequest;
import com.example.demo3.dto.input.RegisterRequest;
import com.example.demo3.dto.output.JwtResponse;
import com.example.demo3.model.User;

public interface AuthService {
    User register(RegisterRequest register);
    JwtResponse login(LoginRequest login);
}
