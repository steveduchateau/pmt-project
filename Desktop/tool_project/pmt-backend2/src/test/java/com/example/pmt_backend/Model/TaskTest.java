package com.example.pmt_backend.Model;


import org.junit.jupiter.api.Test;

import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTaskGettersAndSetters() {
        // Création d'une instance de Task
        Task task = new Task();

        // Test des setters
        task.setId(1L);
        task.setName("Test Task");
        task.setDescription("This is a test task.");
        task.setDueDate(LocalDate.of(2024, 12, 31));
        task.setPriority("HIGH");
        task.setAssignedBy("creator@example.com");
        task.setAssignedTo("assignee@example.com");
        task.setAssignedUsers(Collections.emptyList()); // Exemple avec une liste vide
        task.setStatus("IN_PROGRESS");
        task.setProject(new Project(1L)); // Assignation d'un projet
        task.setCreatedBy("creator@example.com");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        // Test des getters
        assertEquals(1L, task.getId());
        assertEquals("Test Task", task.getName());
        assertEquals("This is a test task.", task.getDescription());
        assertEquals(LocalDate.of(2024, 12, 31), task.getDueDate());
        assertEquals("HIGH", task.getPriority());
        assertEquals("creator@example.com", task.getAssignedBy());
        assertEquals("assignee@example.com", task.getAssignedTo());
        assertTrue(task.getAssignedUsers().isEmpty()); // Vérification que la liste est vide
        assertEquals("IN_PROGRESS", task.getStatus());
        assertNotNull(task.getProject()); // Vérification que le projet est non nul
        assertEquals("creator@example.com", task.getCreatedBy());
        assertNotNull(task.getCreatedAt()); // Vérification que la date de création est non nulle
        assertNotNull(task.getUpdatedAt()); // Vérification que la date de mise à jour est non nulle
    }

    @Test
    void testTaskDefaultConstructor() {
        // Création d'une instance de Task
        Task task = new Task();

        // Vérification que les valeurs par défaut sont null
        assertNull(task.getId());
        assertNull(task.getName());
        assertNull(task.getDescription());
        assertNull(task.getDueDate());
        assertNull(task.getPriority());
        assertNull(task.getAssignedBy());
        assertNull(task.getAssignedTo());
        assertNull(task.getAssignedUsers());
        assertNull(task.getStatus());
        assertNull(task.getProject());
        assertNull(task.getCreatedBy());
        assertNull(task.getCreatedAt());
        assertNull(task.getUpdatedAt());
    }
}
