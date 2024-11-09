package com.example.pmt_backend.Model;

import com.example.pmt_backend.Repository.ProjectRepository;
import com.example.pmt_backend.Repository.TaskRepository;
import com.example.pmt_backend.Repository.UserRepository; // Ajoutez le repository User
import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.model.Task;
import com.example.pmt_backend.model.User; // Assurez-vous d'importer votre modèle User

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TaskIntegrationTest {

    @Autowired
    private TaskRepository taskRepository; // Repository de Task

    @Autowired
    private ProjectRepository projectRepository; // Repository de Project

    @Autowired
    private UserRepository userRepository; // Repository de User ajouté

    @Test
    public void testCreateUpdateAndRetrieveTask() {
        // Création d'un nouvel utilisateur avec un email unique
        User user = new User(); // Créez une instance de l'utilisateur
        user.setEmail("admin_" + System.currentTimeMillis() + "@example.com"); // Utilisez un email unique
        user.setUsername("Admin User " + System.currentTimeMillis()); // Ajoutez un timestamp pour l'unicité
        user.setPassword("securePassword"); // Ajout du mot de passe
        // Enregistrez l'utilisateur dans la base de données
        user = userRepository.save(user);

        // Création d'un nouveau projet pour lier la tâche
        Project project = new Project();
        project.setName("Test Project");
        project.setDescription("A project for testing tasks.");
        project.setStartDate(LocalDate.now());
        project.setCreatorUserId(user.getId()); // Utilisez l'ID de l'utilisateur créé
        project.setCreatorEmail(user.getEmail()); // Utilisez l'email de l'utilisateur
        project.setAdminId(user.getId()); // Lier à l'utilisateur
        project = projectRepository.save(project); // Sauvegarde du projet

        // Création d'une nouvelle tâche
        Task task = new Task();
        task.setName("Test Task");
        task.setDescription("Description of the test task.");
        task.setDueDate(LocalDate.now().plusDays(7));
        task.setPriority("HIGH");
        task.setAssignedBy("assigner@example.com");
        task.setAssignedTo("assignee@example.com");
        task.setStatus("IN_PROGRESS");
        task.setProject(project); // Lier la tâche au projet
        task.setCreatedBy(user.getEmail()); // Utilisez l'email de l'utilisateur
        task.setCreatedAt(LocalDateTime.now());

        // Sauvegarde de la tâche
        Task savedTask = taskRepository.save(task);

        // Vérification que la tâche a été sauvegardée correctement
        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getId()).isNotNull(); // Vérifie que l'ID a été généré
        assertThat(savedTask.getName()).isEqualTo("Test Task");
        assertThat(savedTask.getProject()).isEqualTo(project);

        // Mise à jour de la tâche
        savedTask.setStatus("COMPLETED");
        savedTask.setUpdatedAt(LocalDateTime.now());
        Task updatedTask = taskRepository.save(savedTask);

        // Vérification que la tâche a été mise à jour correctement
        assertThat(updatedTask.getStatus()).isEqualTo("COMPLETED");
        assertThat(updatedTask.getUpdatedAt()).isNotNull();

        // Récupération de la tâche par ID pour vérification
        Task retrievedTask = taskRepository.findById(savedTask.getId()).orElse(null);

        // Vérification que la tâche récupérée est la même que celle sauvegardée
        assertThat(retrievedTask).isNotNull();
        assertThat(retrievedTask.getId()).isEqualTo(savedTask.getId());
        assertThat(retrievedTask.getName()).isEqualTo(savedTask.getName());
        assertThat(retrievedTask.getStatus()).isEqualTo("COMPLETED");
    }
}
