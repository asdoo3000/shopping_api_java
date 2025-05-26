package com.example.demo3.dto.output;

public class JwtResponse {
    public String token;
    public long expire;
    public String type = "Bearer";

    public JwtResponse(String token, long expire) {
        this.token = token;
        this.expire = expire;
    }
}
