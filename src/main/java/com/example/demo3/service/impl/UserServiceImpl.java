package com.example.demo3.service.impl;

import com.example.demo3.common.SortBuilder;
import com.example.demo3.common.SpecificationBuilder;
import com.example.demo3.dto.input.SearchRequest;
import com.example.demo3.model.User;
import com.example.demo3.repository.UserRepository;
import com.example.demo3.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Cacheable(value = "userSearchCache", key = "#filter.toString() + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<User> search(SearchRequest filter, Pageable pageable) {
        Specification<User> spec = SpecificationBuilder.buildFromFilters(filter.getFilters());
        Sort sort = SortBuilder.toSpringSort(filter.getSorts());
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return userRepo.findAll(spec, sortedPageable);
    }

    @Override
    @Cacheable(value = "userGetByIdCache", key = "#id")
    public User getById(String id) {
        return userRepo.findById(UUID.fromString(id)).orElse(null);
    }

    @Override
    public User update(String id, User user) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID format");
        }

        User existingUser = userRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setUsername(user.getUsername());
        existingUser.setPrice(user.getPrice());
        existingUser.setRole(user.getRole());
        existingUser.setDeleted(user.isDeleted());
        return userRepo.save(existingUser);
    }

    @Override
    public void delete(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID format");
        }

        User existingUser = userRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setDeleted(true);
        userRepo.save(existingUser);
    }

}
