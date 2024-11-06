package com.example.pmt_backend.Model;

import com.example.pmt_backend.Repository.ProjectRepository;
import com.example.pmt_backend.Repository.UserRepository; // Assurez-vous d'importer le bon repository
import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.model.User; // Assurez-vous d'importer le modèle User

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProjectIntegrationTest {

    @Autowired
    private ProjectRepository projectRepository; // Repository de projet
    
    @Autowired
    private UserRepository userRepository; // Repository d'utilisateur

    @Test
    @Transactional // S'assure que les données sont annulées après le test
    public void testCreateAndRetrieveProject() {
        // Création d'un nouvel utilisateur
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("creator@example.com");
        user.setPassword("password"); // Assurez-vous de gérer le mot de passe correctement
        userRepository.save(user); // Sauvegarde de l'utilisateur

        // Création d'un nouveau projet
        Project project = new Project();
        project.setName("Test Project");
        project.setDescription("Description du projet");
        project.setStartDate(LocalDate.now());
        project.setCreatorUserId(user.getId()); // Utilisez l'ID de l'utilisateur créé
        project.setCreatorEmail(user.getEmail());
        project.setAdminId(user.getId()); // Utilisez également l'ID de l'utilisateur pour adminId

        // Sauvegarde du projet
        Project savedProject = projectRepository.save(project);

        // Vérification que le projet a été sauvegardé correctement
        assertThat(savedProject).isNotNull();
        assertThat(savedProject.getId()).isNotNull(); // Vérifie que l'ID a été généré
        assertThat(savedProject.getName()).isEqualTo("Test Project");
        assertThat(savedProject.getDescription()).isEqualTo("Description du projet");
        assertThat(savedProject.getStartDate()).isEqualTo(LocalDate.now());
        assertThat(savedProject.getCreatorUserId()).isEqualTo(user.getId());
        assertThat(savedProject.getCreatorEmail()).isEqualTo(user.getEmail());
        assertThat(savedProject.getAdminId()).isEqualTo(user.getId());

        // Récupération du projet par ID pour vérification
        Project retrievedProject = projectRepository.findById(savedProject.getId()).orElse(null);

        // Vérification que le projet récupéré est le même que celui sauvegardé
        assertThat(retrievedProject).isNotNull();
        assertThat(retrievedProject.getId()).isEqualTo(savedProject.getId());
        assertThat(retrievedProject.getName()).isEqualTo(savedProject.getName());
        assertThat(retrievedProject.getDescription()).isEqualTo(savedProject.getDescription());
        assertThat(retrievedProject.getStartDate()).isEqualTo(savedProject.getStartDate());
        assertThat(retrievedProject.getCreatorUserId()).isEqualTo(savedProject.getCreatorUserId());
        assertThat(retrievedProject.getCreatorEmail()).isEqualTo(savedProject.getCreatorEmail());
        assertThat(retrievedProject.getAdminId()).isEqualTo(savedProject.getAdminId());
    }
}
