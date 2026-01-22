package com.bzermeno.cs320.projectoneenhancement.service;

import com.bzermeno.cs320.projectoneenhancement.dto.UserRequest;
import com.bzermeno.cs320.projectoneenhancement.dto.UserResponse;
import com.bzermeno.cs320.projectoneenhancement.mapper.UserMapper;
import com.bzermeno.cs320.projectoneenhancement.model.User;
import com.bzermeno.cs320.projectoneenhancement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse create(UserRequest request) {
        // Business rules and validations would go here
        User user = new User(UUID.randomUUID().toString(), request.getName(), request.getEmail());
        userRepository.save(user);
        return UserMapper.toResponse(user);
    }
}
