package com.example.demo3.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity;
    private BigDecimal price;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;
}
