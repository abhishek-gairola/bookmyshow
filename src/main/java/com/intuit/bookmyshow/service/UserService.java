package com.intuit.bookmyshow.service;

import com.intuit.bookmyshow.entity.User;

import java.util.UUID;

public interface UserService {
    User createUser(User user);
    User updateUser(UUID userId, User userDetails);
    void deleteUser(UUID userId);
}
