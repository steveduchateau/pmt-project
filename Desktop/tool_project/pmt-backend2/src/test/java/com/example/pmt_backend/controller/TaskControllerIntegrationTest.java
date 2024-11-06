package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.Task;
import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectService projectService;

    private Long projectId;

    @BeforeEach
    public void setUp() {
        Project project = new Project();
        project.setName("Nouveau Projet");
        projectId = projectService.createProject(project).getId();
    }

    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task();
        task.setName("Nouvelle Tâche");
        task.setDescription("Ceci est une description de la tâche.");
        task.setDueDate(LocalDate.now().plusDays(7));
        task.setPriority("HIGH");
        task.setAssignedBy("user@example.com");
        task.setAssignedTo("steveduchateau@outlook.fr");
        task.setStatus("IN_PROGRESS");
        task.setCreatedBy("creator@example.com");
        task.setCreatedAt(LocalDateTime.now());

        // Utilisation de JSON formaté pour la création de tâche
        String taskJson = String.format("{\"name\":\"%s\", \"description\":\"%s\", \"dueDate\":\"%s\", \"priority\":\"%s\", \"assignedBy\":\"%s\", \"assignedTo\":\"%s\", \"status\":\"%s\", \"createdBy\":\"%s\", \"createdAt\":\"%s\"}",
                task.getName(), task.getDescription(), task.getDueDate(), task.getPriority(), task.getAssignedBy(), task.getAssignedTo(), task.getStatus(), task.getCreatedBy(), task.getCreatedAt());

        mockMvc.perform(post("/auth/tasks/" + projectId + "/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andExpect(status().isOk());
    }
}
