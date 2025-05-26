package com.example.demo3.specification;

import com.example.demo3.model.Order;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderSpecification {

    public static Specification<Order> hasStatus(String status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Order> orderDateBetween(LocalDateTime from, LocalDateTime to) {
        return (root, query, cb) ->
                (from == null || to == null) ? null : cb.between(root.get("orderDate"), from, to);
    }
}