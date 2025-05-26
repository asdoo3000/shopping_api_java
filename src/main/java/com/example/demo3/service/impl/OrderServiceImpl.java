package com.example.demo3.service.impl;

import com.example.demo3.model.Order;
import com.example.demo3.repository.OrderRepository;
import com.example.demo3.service.OrderService;
import com.example.demo3.specification.OrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> search(String status, LocalDateTime from, LocalDateTime to) {
        Specification<Order> spec = Specification.where(OrderSpecification.hasStatus(status))
                .and(OrderSpecification.orderDateBetween(from, to));
        return orderRepository.findAll(spec);
    }

    @Override
    public Order addOrder(Order order) {
        var saved = orderRepository.save(order);
        return saved;
    }

    @Override
    public Order updateOrder(UUID id, Order updated) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(updated.getStatus());
            order.setOrderDate(updated.getOrderDate());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void deleteOrder(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setDeleted(true);
        orderRepository.save(order);
    }

}
