package com.example.pmt_backend.service;

import com.example.pmt_backend.model.User;
import com.example.pmt_backend.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceIntegrationTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setUsername("testUser");
    }

    @Test
    public void testFindByEmail() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        User foundUser = userService.findByEmail("test@example.com");
        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    public void testExistsByEmail() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        boolean exists = userService.existsByEmail("test@example.com");
        assertTrue(exists);
        verify(userRepository).existsByEmail("test@example.com");
    }

    @Test
    public void testExistsByUsername() {
        when(userRepository.existsByUsername("testUser")).thenReturn(true);

        boolean exists = userService.existsByUsername("testUser");
        assertTrue(exists);
        verify(userRepository).existsByUsername("testUser");
    }

    @Test
    public void testCreateUser() {
        userService.createUser(user);
        verify(userRepository).save(user);
    }

    @Test
    public void testCheckPassword() {
        boolean passwordMatches = userService.checkPassword(user, "password123");
        assertTrue(passwordMatches);

        boolean passwordDoesNotMatch = userService.checkPassword(user, "wrongPassword");
        assertFalse(passwordDoesNotMatch);
    }
}
