package com.example.pmt_backend.service;

import com.example.pmt_backend.DTO.TaskDTO;
import com.example.pmt_backend.model.Task;
import com.example.pmt_backend.model.TaskHistory;
import com.example.pmt_backend.Repository.TaskRepository;
import com.example.pmt_backend.Repository.TaskHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskHistoryRepository taskHistoryRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        // Arrange
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("Test Task");
        taskDTO.setDescription("This is a test task.");
        taskDTO.setPriority("High");
        
        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setName("Test Task");

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        // Act
        Task result = taskService.createTask(taskDTO, "user@example.com");

        // Assert
        assertNotNull(result);
        assertEquals("Test Task", result.getName());
        verify(taskRepository, times(1)).save(any(Task.class));
        verify(taskHistoryRepository, times(1)).save(any(TaskHistory.class));
    }

    @Test
    void testAddTask() {
        // Arrange
        Task task = new Task();
        task.setName("New Task");
        task.setDescription("Description of the task.");
        
        Task savedTask = new Task();
        savedTask.setId(2L);
        savedTask.setName("New Task");

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        // Act
        Task result = taskService.addTask(task, "user@example.com");

        // Assert
        assertNotNull(result);
        assertEquals("New Task", result.getName());
        verify(taskRepository, times(1)).save(task);
        verify(taskHistoryRepository, times(1)).save(any(TaskHistory.class));
    }

    @Test
    void testGetTaskById() {
        // Arrange
        Task task = new Task();
        task.setId(3L);
        task.setName("Task by ID");

        when(taskRepository.findById(3L)).thenReturn(Optional.of(task));

        // Act
        Task result = taskService.getTaskById(3L);

        // Assert
        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals("Task by ID", result.getName());
        verify(taskRepository, times(1)).findById(3L);
    }

    @Test
    void testUpdateTask() {
        // Arrange
        Task existingTask = new Task();
        existingTask.setId(4L);
        existingTask.setName("Existing Task");
        existingTask.setDescription("Old description");
        existingTask.setStatus("Pending");

        Task updatedDetails = new Task();
        updatedDetails.setName("Updated Task");
        updatedDetails.setDescription("New description");
        updatedDetails.setStatus("Completed");

        when(taskRepository.findById(4L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(existingTask);

        // Act
        Task result = taskService.updateTask(4L, updatedDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Task", result.getName());
        assertEquals("New description", result.getDescription());
        assertEquals("Completed", result.getStatus());
        verify(taskRepository, times(1)).save(existingTask);
        verify(taskHistoryRepository, times(1)).save(any(TaskHistory.class));
    }

    @Test
    void testGetAllTasks() {
        // Arrange
        Task task1 = new Task();
        task1.setId(5L);
        task1.setName("Task 1");

        Task task2 = new Task();
        task2.setId(6L);
        task2.setName("Task 2");

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        // Act
        List<Task> result = taskService.getAllTasks();

        // Assert
        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testGetTaskHistory() {
        // Arrange
        TaskHistory history1 = new TaskHistory();
        history1.setId(1L);
        history1.setModifiedAt(LocalDateTime.now());

        TaskHistory history2 = new TaskHistory();
        history2.setId(2L);
        history2.setModifiedAt(LocalDateTime.now());

        when(taskHistoryRepository.findByTaskId(7L)).thenReturn(Arrays.asList(history1, history2));

        // Act
        List<TaskHistory> result = taskService.getTaskHistory(7L);

        // Assert
        assertEquals(2, result.size());
        verify(taskHistoryRepository, times(1)).findByTaskId(7L);
    }
}
