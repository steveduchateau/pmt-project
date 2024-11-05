package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.Task;
import com.example.pmt_backend.model.TaskHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Rollback(false)
public class TaskHistoryRepositoryIntegrationTest {

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

    @Autowired
    private TaskRepository taskRepository; // Pour créer une tâche liée à TaskHistory

    private Task task;

    @BeforeEach
    public void setUp() {
        taskHistoryRepository.deleteAll();
        taskRepository.deleteAll();

        // Créer une tâche de base pour les tests
        task = new Task();
        task.setName("Test Task");
        task.setDescription("Description of Test Task");
        task = taskRepository.save(task);
    }

    @Test
    public void testSaveAndFindByTaskId() {
        // Créer un historique de tâche
        TaskHistory taskHistory = new TaskHistory();
        taskHistory.setTask(task);
        taskHistory.setAction("CREATED");
        taskHistory.setModifiedBy("user@example.com");
        taskHistory.setModifiedAt(LocalDateTime.now());
        taskHistory.setOldDescription(null);
        taskHistory.setNewDescription("Initial task description");
        taskHistory.setOldStatus(null);
        taskHistory.setNewStatus("NEW");
        taskHistory.setCreatedAt(LocalDateTime.now());
        taskHistory.setUpdatedAt(LocalDateTime.now());

        // Sauvegarder l'historique
        taskHistoryRepository.save(taskHistory);

        // Récupérer les historiques de tâche par l'ID de la tâche
        List<TaskHistory> taskHistories = taskHistoryRepository.findByTaskId(task.getId());

        // Vérifier que l'historique est récupéré correctement
        assertThat(taskHistories).hasSize(1);
        assertThat(taskHistories.get(0).getAction()).isEqualTo("CREATED");
        assertThat(taskHistories.get(0).getModifiedBy()).isEqualTo("user@example.com");
    }

    @Test
    public void testDeleteTaskHistory() {
        // Créer un historique de tâche
        TaskHistory taskHistory = new TaskHistory();
        taskHistory.setTask(task);
        taskHistory.setAction("UPDATED");
        taskHistory.setModifiedBy("user2@example.com");
        taskHistory.setModifiedAt(LocalDateTime.now());
        taskHistory.setOldDescription("Initial task description");
        taskHistory.setNewDescription("Updated task description");
        taskHistory.setOldStatus("NEW");
        taskHistory.setNewStatus("IN_PROGRESS");
        taskHistory.setCreatedAt(LocalDateTime.now());
        taskHistory.setUpdatedAt(LocalDateTime.now());

        // Sauvegarder l'historique
        taskHistory = taskHistoryRepository.save(taskHistory);

        // Vérifier que l'historique existe avant la suppression
        List<TaskHistory> taskHistoriesBeforeDelete = taskHistoryRepository.findByTaskId(task.getId());
        assertThat(taskHistoriesBeforeDelete).hasSize(1);

        // Supprimer l'historique
        taskHistoryRepository.delete(taskHistory);

        // Vérifier que l'historique n'existe plus après la suppression
        List<TaskHistory> taskHistoriesAfterDelete = taskHistoryRepository.findByTaskId(task.getId());
        assertThat(taskHistoriesAfterDelete).isEmpty();
    }
}
