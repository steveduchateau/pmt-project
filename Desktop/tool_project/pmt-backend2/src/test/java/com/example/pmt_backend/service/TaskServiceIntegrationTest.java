package com.example.pmt_backend.service;

import com.example.pmt_backend.DTO.TaskDTO;
import com.example.pmt_backend.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TaskServiceIntegrationTest {

    @Autowired
    private TaskService taskService;

    private TaskDTO taskDTO;

    @BeforeEach
    public void setUp() {
        taskDTO = new TaskDTO();
        taskDTO.setName("Test Task");
        taskDTO.setDescription("Description of Test Task");
        taskDTO.setDueDate(LocalDate.now().plusDays(7));
        taskDTO.setPriority("High");
        taskDTO.setAssignedTo("user@example.com");
    }

    @Test
    public void testCreateTask() {
        Task createdTask = taskService.createTask(taskDTO, "assigner@example.com");
        assertNotNull(createdTask.getId());
        assertEquals(taskDTO.getName(), createdTask.getName());
    }

    @Test
    public void testGetAllTasks() {
        taskService.createTask(taskDTO, "assigner@example.com");
        List<Task> tasks = taskService.getAllTasks();

        // Vérifie que la tâche récemment ajoutée est bien présente sans NullPointerException
        assertTrue(tasks.stream().anyMatch(task -> task.getName() != null && task.getName().equals("Test Task")));
    }

    @Test
    public void testGetTaskById() {
        Task createdTask = taskService.createTask(taskDTO, "assigner@example.com");
        Task foundTask = taskService.getTaskById(createdTask.getId());
        assertNotNull(foundTask);
        assertEquals(createdTask.getId(), foundTask.getId());
    }

    @Test
    public void testUpdateTask() {
        Task createdTask = taskService.createTask(taskDTO, "assigner@example.com");
        Task updatedTaskDetails = new Task();
        updatedTaskDetails.setId(createdTask.getId());
        updatedTaskDetails.setName("Updated Task");
        updatedTaskDetails.setDescription("Updated description");
        updatedTaskDetails.setDueDate(LocalDate.now().plusDays(5));
        updatedTaskDetails.setPriority("Medium");

        Task updatedTask = taskService.updateTask(createdTask.getId(), updatedTaskDetails);
        assertNotNull(updatedTask);
        assertEquals("Updated Task", updatedTask.getName());
    }
}
