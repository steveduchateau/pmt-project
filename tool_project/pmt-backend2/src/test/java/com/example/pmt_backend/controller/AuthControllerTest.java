package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.User;
import com.example.pmt_backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        // Setup
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        User foundUser = new User();
        foundUser.setId(1L);
        foundUser.setEmail("test@example.com");

        when(userService.findByEmail(user.getEmail())).thenReturn(foundUser);
        when(userService.checkPassword(foundUser, user.getPassword())).thenReturn(true);

        // Act
        ResponseEntity<Map<String, Object>> response = authController.login(user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody(), "Response body should not be null"); // Check that getBody() is not null
        if (response.getBody() != null) {
            assertEquals("Login successful", response.getBody().get("message"));
            assertEquals(foundUser.getId(), response.getBody().get("Id"));
            assertEquals(foundUser.getEmail(), response.getBody().get("email"));
        }
        verify(userService).setCurrentUserEmail(foundUser.getEmail());
    }

    @Test
    void testLoginInvalidEmailOrPassword() {
        // Setup
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("wrongpassword");

        when(userService.findByEmail(user.getEmail())).thenReturn(null);

        // Act
        ResponseEntity<Map<String, Object>> response = authController.login(user);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody(), "Response body should not be null"); // Check that getBody() is not null
        if (response.getBody() != null) {
            assertEquals("Invalid email or password", response.getBody().get("message"));
        }
    }

    @Test
    void testRegisterSuccess() {
        // Setup
        User user = new User();
        user.setEmail("newuser@example.com");
        user.setUsername("newuser");

        when(userService.existsByEmail(user.getEmail())).thenReturn(false);
        when(userService.existsByUsername(user.getUsername())).thenReturn(false);

        // Act
        ResponseEntity<?> response = authController.register(user);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody(), "Response body should not be null"); // Check that getBody() is not null
        if (response.getBody() instanceof ApiResponse) {
            assertEquals("User registered successfully", ((ApiResponse) response.getBody()).getMessage());
        }
        verify(userService).createUser(user);
    }

    @Test
    void testRegisterEmailAlreadyInUse() {
        // Setup
        User user = new User();
        user.setEmail("existing@example.com");
        user.setUsername("newuser");

        when(userService.existsByEmail(user.getEmail())).thenReturn(true);

        // Act
        ResponseEntity<?> response = authController.register(user);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody(), "Response body should not be null"); // Check that getBody() is not null
        if (response.getBody() instanceof ApiResponse) {
            assertEquals("Email already in use", ((ApiResponse) response.getBody()).getMessage());
        }
    }

    @Test
    void testRegisterUsernameAlreadyInUse() {
        // Setup
        User user = new User();
        user.setEmail("newuser@example.com");
        user.setUsername("existinguser");

        when(userService.existsByEmail(user.getEmail())).thenReturn(false);
        when(userService.existsByUsername(user.getUsername())).thenReturn(true);

        // Act
        ResponseEntity<?> response = authController.register(user);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody(), "Response body should not be null"); // Check that getBody() is not null
        if (response.getBody() instanceof ApiResponse) {
            assertEquals("Username already in use", ((ApiResponse) response.getBody()).getMessage());
        }
    }
}
