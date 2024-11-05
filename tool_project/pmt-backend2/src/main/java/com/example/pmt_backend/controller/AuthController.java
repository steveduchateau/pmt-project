package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.User;
import com.example.pmt_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        User foundUser = userService.findByEmail(user.getEmail());
        Map<String, Object> response = new HashMap<>();

        if (foundUser == null) {
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        if (!userService.checkPassword(foundUser, user.getPassword())) {
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        userService.setCurrentUserEmail(foundUser.getEmail()); // Stocker l'email de l'utilisateur connecté

        response.put("message", "Login successful");
        response.put("Id", foundUser.getId());
        response.put("email", foundUser.getEmail());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            System.out.println("Email already in use");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Email already in use"));
        }
        if (userService.existsByUsername(user.getUsername())) {
            System.out.println("Username already in use");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Username already in use"));
        }

        userService.createUser(user);
        System.out.println("User registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User registered successfully"));
    }
}

// Classe ApiResponse pour encapsuler les réponses
class ApiResponse {
    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
