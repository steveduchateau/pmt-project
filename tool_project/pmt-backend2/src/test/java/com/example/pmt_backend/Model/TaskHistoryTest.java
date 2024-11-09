package com.example.pmt_backend.Model;


import org.junit.jupiter.api.Test;

import com.example.pmt_backend.model.Task;
import com.example.pmt_backend.model.TaskHistory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskHistoryTest {

    @Test
    void testTaskHistoryGettersAndSetters() {
        // Création d'une instance de TaskHistory
        TaskHistory taskHistory = new TaskHistory();

        // Test des setters
        taskHistory.setId(1L);
        taskHistory.setTask(new Task()); // Assignation d'une nouvelle tâche
        taskHistory.setAction("CREATED");
        taskHistory.setModifiedBy("user@example.com");
        taskHistory.setModifiedAt(LocalDateTime.now());
        taskHistory.setOldDescription("Old description");
        taskHistory.setNewDescription("New description");
        taskHistory.setOldStatus("PENDING");
        taskHistory.setNewStatus("COMPLETED");

        // Test des getters
        assertEquals(1L, taskHistory.getId());
        assertNotNull(taskHistory.getTask()); // Vérification que la tâche est non nulle
        assertEquals("CREATED", taskHistory.getAction());
        assertEquals("user@example.com", taskHistory.getModifiedBy());
        assertNotNull(taskHistory.getModifiedAt()); // Vérification que la date de modification est non nulle
        assertEquals("Old description", taskHistory.getOldDescription());
        assertEquals("New description", taskHistory.getNewDescription());
        assertEquals("PENDING", taskHistory.getOldStatus());
        assertEquals("COMPLETED", taskHistory.getNewStatus());
    }

    @Test
    void testTaskHistoryDefaultConstructor() {
        // Création d'une instance de TaskHistory
        TaskHistory taskHistory = new TaskHistory();

        // Vérification que les valeurs par défaut sont null
        assertNull(taskHistory.getId());
        assertNull(taskHistory.getTask());
        assertNull(taskHistory.getAction());
        assertNull(taskHistory.getModifiedBy());
        assertNull(taskHistory.getModifiedAt());
        assertNull(taskHistory.getOldDescription());
        assertNull(taskHistory.getNewDescription());
        assertNull(taskHistory.getOldStatus());
        assertNull(taskHistory.getNewStatus());
    }
}
