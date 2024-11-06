package com.example.pmt_backend.Repository;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.pmt_backend.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        // Création d'un utilisateur valide
        user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        user.setUsername("testuser");
        userRepository.save(user);
    }

    @Test
    public void testFindByEmail() {
        // Récupération de l'utilisateur par email
        User foundUser = userRepository.findByEmail(user.getEmail());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void testExistsByEmail() {
        // Vérification de l'existence de l'utilisateur par email
        boolean exists = userRepository.existsByEmail(user.getEmail());
        assertThat(exists).isTrue();
    }

    @Test
    public void testExistsByUsername() {
        // Vérification de l'existence de l'utilisateur par username
        boolean exists = userRepository.existsByUsername(user.getUsername());
        assertThat(exists).isTrue();
    }

    @Test
    public void testEmailDoesNotExist() {
        // Vérification que l'email d'un utilisateur inexistant ne renvoie pas true
        boolean exists = userRepository.existsByEmail("nonexistent@example.com");
        assertThat(exists).isFalse();
    }

    @Test
    public void testUsernameDoesNotExist() {
        // Vérification que le username d'un utilisateur inexistant ne renvoie pas true
        boolean exists = userRepository.existsByUsername("nonexistentuser");
        assertThat(exists).isFalse();
    }
}
