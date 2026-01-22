package com.bzermeno.cs320.projectoneenhancement.service;

import com.bzermeno.cs320.projectoneenhancement.dto.UserRequest;
import com.bzermeno.cs320.projectoneenhancement.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse create(UserRequest request);
}
