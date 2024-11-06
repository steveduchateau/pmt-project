package com.example.pmt_backend.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.pmt_backend.Repository.UserRepository;
import com.example.pmt_backend.model.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // Assure que chaque test s'exécute dans une transaction qui est annulée à la fin du test
public class UserIntegrationTest {

    @Autowired
    private UserRepository userRepository; // Repository de User

    private User user;

    @BeforeEach
    public void setUp() {
        // Création d'un nouvel utilisateur avec un email unique
        user = new User();
        user.setEmail("unique_testuser95131@example.com"); // Adresse e-mail unique
        user.setPassword("securepassword"); // Assurez-vous de ne pas utiliser de mots de passe en clair en production
        user.setUsername("testuser_95131"); // Nom d'utilisateur unique
    }

    @Test
    public void testCreateAndRetrieveUser() {
        // Sauvegarde de l'utilisateur
        User savedUser = userRepository.save(user);

        // Vérification que l'utilisateur a été sauvegardé correctement
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull(); // Vérifie que l'ID a été généré
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(savedUser.getCreatedAt()).isNotNull(); // Vérifie que la date de création a été définie

        // Récupération de l'utilisateur par ID pour vérification
        User retrievedUser = userRepository.findById(savedUser.getId()).orElse(null);

        // Vérification que l'utilisateur récupéré est le même que celui sauvegardé
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getId()).isEqualTo(savedUser.getId());
        assertThat(retrievedUser.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(retrievedUser.getUsername()).isEqualTo(savedUser.getUsername());
    }
}
