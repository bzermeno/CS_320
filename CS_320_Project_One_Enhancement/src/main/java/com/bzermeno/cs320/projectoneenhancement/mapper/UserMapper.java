package com.bzermeno.cs320.projectoneenhancement.mapper;

import com.bzermeno.cs320.projectoneenhancement.dto.UserRequest;
import com.bzermeno.cs320.projectoneenhancement.dto.UserResponse;
import com.bzermeno.cs320.projectoneenhancement.model.User;

public final class UserMapper {

    private UserMapper() {}

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    public static User fromRequest(UserRequest request, String id) {
        return new User(id, request.getName(), request.getEmail());
    }
}
