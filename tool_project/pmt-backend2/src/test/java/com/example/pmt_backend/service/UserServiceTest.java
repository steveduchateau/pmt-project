package com.example.pmt_backend.service;

import com.example.pmt_backend.Repository.UserRepository;
import com.example.pmt_backend.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setUsername("testUser");
        testUser.setPassword("password123");
    }

    @Test
    void findByEmail_UserExists_ReturnsUser() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(testUser);

        // Act
        User result = userService.findByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void existsByEmail_EmailExists_ReturnsTrue() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act
        boolean result = userService.existsByEmail(email);

        // Assert
        assertTrue(result);
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void existsByEmail_EmailDoesNotExist_ReturnsFalse() {
        // Arrange
        String email = "nonexistent@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act
        boolean result = userService.existsByEmail(email);

        // Assert
        assertFalse(result);
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void existsByUsername_UsernameExists_ReturnsTrue() {
        // Arrange
        String username = "testUser";
        when(userRepository.existsByUsername(username)).thenReturn(true);

        // Act
        boolean result = userService.existsByUsername(username);

        // Assert
        assertTrue(result);
        verify(userRepository, times(1)).existsByUsername(username);
    }

    @Test
    void createUser_SavesUserSuccessfully() {
        // Act
        userService.createUser(testUser);

        // Assert
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void setCurrentUserEmailAndGetCurrentUserEmail_WorksCorrectly() {
        // Arrange
        String email = "currentuser@example.com";

        // Act
        userService.setCurrentUserEmail(email);
        String result = userService.getCurrentUserEmail();

        // Assert
        assertEquals(email, result);
    }

    @Test
    void checkPassword_CorrectPassword_ReturnsTrue() {
        // Arrange
        String rawPassword = "password123";

        // Act
        boolean result = userService.checkPassword(testUser, rawPassword);

        // Assert
        assertTrue(result);
    }

    @Test
    void checkPassword_IncorrectPassword_ReturnsFalse() {
        // Arrange
        String rawPassword = "wrongPassword";

        // Act
        boolean result = userService.checkPassword(testUser, rawPassword);

        // Assert
        assertFalse(result);
    }
}
