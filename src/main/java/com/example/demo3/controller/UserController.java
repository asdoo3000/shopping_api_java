package com.example.demo3.controller;

import com.example.demo3.dto.input.SearchRequest;
import com.example.demo3.model.User;
import com.example.demo3.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/search")
    public Page<User> searchUsers(
            @RequestBody SearchRequest filter,
            @PageableDefault(size = 10) Pageable pageable) {

        return userService.search(filter, pageable);
    }
}
