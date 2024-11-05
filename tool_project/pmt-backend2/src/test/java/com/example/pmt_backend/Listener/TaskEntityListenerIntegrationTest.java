package com.example.pmt_backend.Listener;

import com.example.pmt_backend.model.Task;
import com.example.pmt_backend.Repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TaskEntityListenerIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testPrePersistListener() {
        // Création d'une nouvelle tâche
        Task task = new Task();
        task.setName("Test Task");
        task.setDescription("Description de la tâche");
        
        // Sauvegarde de la tâche
        Task savedTask = taskRepository.save(task);

        // Vérification que les champs createdAt et updatedAt sont définis
        assertNotNull(savedTask.getCreatedAt(), "Le champ createdAt devrait être défini lors de la création");
        assertNotNull(savedTask.getUpdatedAt(), "Le champ updatedAt devrait être défini lors de la création");
    }


}
