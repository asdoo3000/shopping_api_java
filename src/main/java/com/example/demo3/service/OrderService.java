package com.example.demo3.service;

import com.example.demo3.model.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> search(String status, LocalDateTime from, LocalDateTime to);
    Order addOrder(Order order);
    Order updateOrder(UUID id, Order updated);
    public void deleteOrder(UUID id);
}
