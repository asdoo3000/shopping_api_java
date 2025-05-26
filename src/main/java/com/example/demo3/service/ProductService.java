package com.example.demo3.service;

import com.example.demo3.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> search(String status, UUID userId, LocalDateTime from, LocalDateTime to);
    Product add(Product order);
    Product update(UUID id, Product updated);
    public void delete(UUID id);
}
