package com.example.demo3.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    @Column(name = "is_deleted")
    private boolean isDeleted = false;
}
