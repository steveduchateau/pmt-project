package com.example.pmt_backend.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class TaskRepositoryTests {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private Project project;

    @BeforeEach
    public void setUp() {
        // Création d'un projet valide
        project = new Project();
        project.setName("Project 1");
        project.setDescription("Description of Project 1");
        projectRepository.save(project);
    }

    @Test
    public void testFindByProjectId() {
        // Création d'une tâche associée au projet
        Task task = new Task();
        task.setName("Task 1");
        task.setDescription("Description of Task 1");
        task.setDueDate(LocalDate.now().plusDays(7));
        task.setPriority("HIGH");
        task.setAssignedBy("user@example.com");
        task.setAssignedTo("assignee@example.com");
        task.setStatus("IN_PROGRESS");
        task.setProject(project); // Associer la tâche au projet
        task.setCreatedAt(LocalDateTime.now());
        task.setCreatedBy("creator@example.com");
        taskRepository.save(task);

        // Récupération des tâches par ID de projet
        List<Task> tasks = taskRepository.findByProjectId(project.getId());
        assertThat(tasks).isNotEmpty();
        assertThat(tasks.get(0).getName()).isEqualTo("Task 1");
    }

    @Test
    public void testFindByProjectIdAndStatus() {
        // Création d'une tâche associée au projet
        Task task = new Task();
        task.setName("Task 2");
        task.setDescription("Description of Task 2");
        task.setDueDate(LocalDate.now().plusDays(7));
        task.setPriority("MEDIUM");
        task.setAssignedBy("user@example.com");
        task.setAssignedTo("assignee@example.com");
        task.setStatus("COMPLETED");
        task.setProject(project); // Associer la tâche au projet
        task.setCreatedAt(LocalDateTime.now());
        task.setCreatedBy("creator@example.com");
        taskRepository.save(task);

        // Récupération des tâches par ID de projet et état
        List<Task> tasks = taskRepository.findByProjectIdAndStatus(project.getId(), "COMPLETED");
        assertThat(tasks).isNotEmpty();
        assertThat(tasks.get(0).getName()).isEqualTo("Task 2");
    }
}
