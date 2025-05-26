package com.example.demo3.common;

import com.example.demo3.dto.input.SortRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class SortBuilder {
    public static Sort toSpringSort(List<SortRequest> sortRequests) {
        if (sortRequests == null || sortRequests.isEmpty()) return Sort.unsorted();

        List<Sort.Order> orders = sortRequests.stream()
                .map(req -> {
                    Sort.Direction dir = "desc".equalsIgnoreCase(req.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
                    return new Sort.Order(dir, req.getField());
                })
                .toList();

        return Sort.by(orders);
    }
}
