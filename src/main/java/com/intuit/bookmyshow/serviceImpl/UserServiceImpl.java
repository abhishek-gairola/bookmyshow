package com.intuit.bookmyshow.serviceImpl;

import com.intuit.bookmyshow.entity.User;
import com.intuit.bookmyshow.repository.UserRepository;
import com.intuit.bookmyshow.service.UserService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(UUID userId, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDetails.getName());
            user.setAddress(userDetails.getAddress());
            user.setEmail(userDetails.getEmail());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

}
