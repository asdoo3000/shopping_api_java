package com.example.demo3.service.impl;

import com.example.demo3.model.Product;
import com.example.demo3.repository.ProductRepository;
import com.example.demo3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl  implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> search(String status, UUID userId, LocalDateTime from, LocalDateTime to) {
        return List.of();
    }

    @Override
    public Product add(Product order) {
        var saved = productRepository.save(order);
        return saved;
    }

    @Override
    public Product update(UUID id, Product updated) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
