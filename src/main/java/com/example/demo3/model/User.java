package com.example.demo3.model;

import com.example.demo3.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(unique = true)
    private String username;

    private String password;
    private String name;
    private BigDecimal price;
    private UserRole role;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;
}
