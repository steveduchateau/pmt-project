package com.example.pmt_backend.service;

import com.example.pmt_backend.model.User;
import com.example.pmt_backend.Repository.UserRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void save(User user) {
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
