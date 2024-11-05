package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Rollback(false)
public class TaskRepositoryIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository; // Pour créer un projet lié aux tâches

    private Project project;

    @BeforeEach
    public void setUp() {
        taskRepository.deleteAll();
        projectRepository.deleteAll();

        // Créer un projet pour les tests
        project = new Project();
        project.setName("Test Project");
        project = projectRepository.save(project);
    }

    @Test
    public void testFindByProjectId() {
        // Créer des tâches associées au projet
        Task task1 = createTask("Task 1", "IN_PROGRESS", project);
        Task task2 = createTask("Task 2", "COMPLETED", project);
        taskRepository.save(task1);
        taskRepository.save(task2);

        // Récupérer les tâches par ID de projet
        List<Task> tasks = taskRepository.findByProjectId(project.getId());

        // Vérifier que les tâches sont correctement récupérées
        assertThat(tasks).hasSize(2);
        assertThat(tasks.get(0).getName()).isEqualTo("Task 1");
        assertThat(tasks.get(1).getName()).isEqualTo("Task 2");
    }

    @Test
    public void testFindByProjectIdWithPagination() {
        // Créer plusieurs tâches associées au projet
        for (int i = 1; i <= 5; i++) {
            Task task = createTask("Task " + i, "IN_PROGRESS", project);
            taskRepository.save(task);
        }

        // Récupérer les tâches par ID de projet avec pagination (2 tâches par page)
        Pageable pageable = PageRequest.of(0, 2);
        Page<Task> taskPage = taskRepository.findByProjectId(project.getId(), pageable);

        // Vérifier que la pagination fonctionne correctement
        assertThat(taskPage.getTotalElements()).isEqualTo(5);
        assertThat(taskPage.getTotalPages()).isEqualTo(3);
        assertThat(taskPage.getContent()).hasSize(2);
        assertThat(taskPage.getContent().get(0).getName()).isEqualTo("Task 1");
    }

    @Test
    public void testFindByProjectIdAndStatus() {
        // Créer des tâches avec différents statuts
        Task task1 = createTask("Task 1", "IN_PROGRESS", project);
        Task task2 = createTask("Task 2", "COMPLETED", project);
        taskRepository.save(task1);
        taskRepository.save(task2);

        // Récupérer les tâches par ID de projet et statut
        List<Task> inProgressTasks = taskRepository.findByProjectIdAndStatus(project.getId(), "IN_PROGRESS");

        // Vérifier que seules les tâches avec le statut "IN_PROGRESS" sont récupérées
        assertThat(inProgressTasks).hasSize(1);
        assertThat(inProgressTasks.get(0).getName()).isEqualTo("Task 1");
        assertThat(inProgressTasks.get(0).getStatus()).isEqualTo("IN_PROGRESS");
    }

    // Méthode utilitaire pour créer une tâche
    private Task createTask(String name, String status, Project project) {
        Task task = new Task();
        task.setName(name);
        task.setDescription("Description of " + name);
        task.setDueDate(LocalDate.now().plusDays(5));
        task.setPriority("MEDIUM");
        task.setAssignedBy("assigner@example.com");
        task.setAssignedTo("assignee@example.com");
        task.setStatus(status);
        task.setProject(project);
        task.setCreatedBy("creator@example.com");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return task;
    }
}
