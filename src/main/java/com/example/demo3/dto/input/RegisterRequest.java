package com.example.demo3.dto.input;

import org.hibernate.validator.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank(message = "Username is required")
    public String username;
    @NotBlank(message = "Password is required")
    public String password;
    @NotBlank(message = "Name is required")
    public String name;
}
