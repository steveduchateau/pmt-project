package com.example.pmt_backend.Model;
import com.example.pmt_backend.Repository.TaskHistoryRepository;
import com.example.pmt_backend.Repository.TaskRepository;
import com.example.pmt_backend.model.Task;
import com.example.pmt_backend.model.TaskHistory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TaskHistoryIntegrationTest {

    @Autowired
    private TaskRepository taskRepository; // Repository de Task

    @Autowired
    private TaskHistoryRepository taskHistoryRepository; // Repository de TaskHistory

    @Test
    public void testCreateAndRetrieveTaskHistory() {
        // Création d'une nouvelle tâche pour lier l'historique
        Task task = new Task();
        task.setName("Sample Task");
        task.setDescription("Initial description");
        task.setPriority("MEDIUM");
        task.setAssignedBy("assigner@example.com");
        task.setAssignedTo("assignee@example.com");
        task.setStatus("IN_PROGRESS");
        task.setCreatedBy("creator@example.com");
        task.setCreatedAt(LocalDateTime.now());

        // Sauvegarde de la tâche
        Task savedTask = taskRepository.save(task);

        // Création d'une nouvelle entrée d'historique pour la tâche
        TaskHistory taskHistory = new TaskHistory();
        taskHistory.setTask(savedTask); // Lier à la tâche
        taskHistory.setAction("CREATED");
        taskHistory.setModifiedBy("modifier@example.com");
        taskHistory.setModifiedAt(LocalDateTime.now());
        taskHistory.setOldDescription(null); // Aucune ancienne description pour une nouvelle tâche
        taskHistory.setNewDescription(savedTask.getDescription());
        taskHistory.setOldStatus(null); // Aucun ancien statut pour une nouvelle tâche
        taskHistory.setNewStatus(savedTask.getStatus());
        taskHistory.setCreatedAt(LocalDateTime.now());

        // Sauvegarde de l'historique de la tâche
        TaskHistory savedTaskHistory = taskHistoryRepository.save(taskHistory);

        // Vérification que l'historique a été sauvegardé correctement
        assertThat(savedTaskHistory).isNotNull();
        assertThat(savedTaskHistory.getId()).isNotNull(); // Vérifie que l'ID a été généré
        assertThat(savedTaskHistory.getTask()).isEqualTo(savedTask);
        assertThat(savedTaskHistory.getAction()).isEqualTo("CREATED");
        assertThat(savedTaskHistory.getNewDescription()).isEqualTo(savedTask.getDescription());
        assertThat(savedTaskHistory.getNewStatus()).isEqualTo(savedTask.getStatus());

        // Récupération de l'historique par ID pour vérification
        TaskHistory retrievedTaskHistory = taskHistoryRepository.findById(savedTaskHistory.getId()).orElse(null);

        // Vérification que l'historique récupéré est le même que celui sauvegardé
        assertThat(retrievedTaskHistory).isNotNull();
        assertThat(retrievedTaskHistory.getId()).isEqualTo(savedTaskHistory.getId());
        assertThat(retrievedTaskHistory.getAction()).isEqualTo(savedTaskHistory.getAction());
        assertThat(retrievedTaskHistory.getNewDescription()).isEqualTo(savedTaskHistory.getNewDescription());
    }
}
