package com.bzermeno.cs320.projectoneenhancement.repository;

import com.bzermeno.cs320.projectoneenhancement.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    User save(User user);
    Optional<User> findById(String id);
}
