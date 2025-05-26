package com.example.demo3.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    @GetMapping("/")
    public String Hello() {
        return "Hello!";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam String comment) {
        return "You said: " + comment;
    }
}
