package com.example.demo3.service;

import com.example.demo3.dto.input.FilterRequest;
import com.example.demo3.dto.input.SearchRequest;
import com.example.demo3.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Page<User> search(SearchRequest filter, Pageable pageable);

    User getById(String id);

    User update(String id, User user);

    void delete(String id);

}
