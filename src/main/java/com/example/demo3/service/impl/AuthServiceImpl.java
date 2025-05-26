package com.example.demo3.service.impl;

import com.example.demo3.dto.input.LoginRequest;
import com.example.demo3.dto.input.RegisterRequest;
import com.example.demo3.dto.output.JwtResponse;
import com.example.demo3.enums.UserRole;
import com.example.demo3.model.User;
import com.example.demo3.repository.UserRepository;
import com.example.demo3.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class AuthServiceImpl  implements AuthService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expire-minute}")
    private long expireMinute;

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public User register(RegisterRequest register) {
        User user = new User();
        user.setUsername(register.username);
        user.setPassword(encoder.encode(register.password));
        user.setName(register.name);
        user.setRole(UserRole.ADMIN);
        return userRepo.save(user);
    }

    @Override
    public JwtResponse login(LoginRequest login) {
        User user = userRepo.findByUsername(login.username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!isPasswordMatch(login.password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Date expire = getExpirationDate();
        String token = generateToken(user.getUsername(), expire);
        return new JwtResponse(token, expire.getTime());
    }

    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + expireMinute * 60 * 1000);
    }

    private String generateToken(String username, Date expireDate) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
