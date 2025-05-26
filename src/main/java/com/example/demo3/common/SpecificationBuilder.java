package com.example.demo3.common;

import com.example.demo3.dto.input.FilterRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SpecificationBuilder {
    public static <T> Specification<T> buildFromFilters(List<FilterRequest> filters) {
        Specification<T> spec = Specification.where(null);

        for (FilterRequest filter : filters) {
            String field = filter.getField();
            String op = filter.getOp();

            switch (op) {
                case "like" -> spec = spec.and((root, query, cb) ->
                        cb.like(cb.lower(root.get(field)), "%" + filter.getValue().toString().toLowerCase() + "%"));

                case "=" -> spec = spec.and((root, query, cb) ->
                        cb.equal(root.get(field), filter.getValue()));

                case "between" -> {
                    Comparable from = (Comparable) filter.getFrom();
                    Comparable to = (Comparable) filter.getTo();
                    spec = spec.and((root, query, cb) -> cb.between(root.get(field), from, to));
                }
            }
        }

        return spec;
    }
}