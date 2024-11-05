package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.User;
import com.example.pmt_backend.Repository.UserRepository; // Assurez-vous d'importer le repository User
import org.junit.jupiter.api.BeforeEach; // Importez cette classe
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles; // Importez cette classe
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Indique à Spring d'utiliser le profil "test"
public class ProjectControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository; // Ajout du repository User

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll(); // Assurez-vous que la base de données est vide avant chaque test
    }

    @Test
    public void testCreateProject() throws Exception {
        // Création d'un utilisateur pour le test
        User user = new User();
        user.setEmail("testuser" + System.currentTimeMillis() + "@example.com"); // Utilisation d'un timestamp pour l'unicité
        user.setUsername("Test User");
        user.setPassword("securePassword");
        user = userRepository.save(user); // Enregistrement de l'utilisateur

        // Préparez la requête JSON pour le projet en utilisant l'ID de l'utilisateur créé
        String projectJson = String.format("{\"name\": \"Test Project\", \"creatorUserId\": %d, \"adminId\": %d}", 
                                             user.getId(), user.getId()); // Assurez-vous que adminId est également renseigné

        // Exécutez la requête POST et vérifiez le statut de la réponse
        mockMvc.perform(post("/auth/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(projectJson))
                .andExpect(status().isOk());
    }
}
