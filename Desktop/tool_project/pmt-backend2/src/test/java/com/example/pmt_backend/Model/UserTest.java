package com.example.pmt_backend.Model;


import org.junit.jupiter.api.Test;

import com.example.pmt_backend.model.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        // Création d'une instance de User
        User user = new User();

        // Test des setters
        user.setId(1L);
        user.setEmail("user@example.com");
        user.setPassword("securepassword");
        user.setUsername("testuser");

        // Test des getters
        assertEquals(1L, user.getId());
        assertEquals("user@example.com", user.getEmail());
        assertEquals("securepassword", user.getPassword());
        assertEquals("testuser", user.getUsername());
    }

    @Test
    void testUserDefaultConstructor() {
        // Création d'une instance de User
        User user = new User();

        // Vérification que createdAt n'est pas null et est initialisé à l'heure actuelle
        assertNotNull(user.getCreatedAt());

        // Vérification que createdAt est dans une seconde de la création
        LocalDateTime now = LocalDateTime.now();
        assertTrue(user.getCreatedAt().isAfter(now.minusSeconds(1)) && user.getCreatedAt().isBefore(now.plusSeconds(1)));
    }
}
