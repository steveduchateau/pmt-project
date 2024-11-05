package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.TaskHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskHistoryRepositoryTest {

    private TaskHistoryRepository taskHistoryRepository;

    @BeforeEach
    public void setUp() {
        // Créer un mock de TaskHistoryRepository
        taskHistoryRepository = Mockito.mock(TaskHistoryRepository.class);
    }

    @Test
    public void testFindById() {
        Long taskId = 1L; // ID de la tâche à tester
        String expectedDescription = "Description de la tâche"; // Description attendue
        TaskHistory taskHistory = new TaskHistory(); // Créer une instance de TaskHistory

        // Initialiser les valeurs de l'objet taskHistory
        taskHistory.setId(taskId);
        taskHistory.setNewDescription(expectedDescription);

        // Configurer le mock pour retourner un Optional contenant la tâche
        Mockito.when(taskHistoryRepository.findById(taskId)).thenReturn(Optional.of(taskHistory));

        // Appeler la méthode findById
        Optional<TaskHistory> optionalTaskHistory = taskHistoryRepository.findById(taskId);

        // Vérifier que la tâche est présente
        assertTrue(optionalTaskHistory.isPresent(), "La tâche doit être présente");

        // Récupérer la tâche et vérifier les valeurs
        TaskHistory foundTaskHistory = optionalTaskHistory.get();
        assertEquals(taskId, foundTaskHistory.getId(), "L'ID de la tâche doit correspondre");
        assertEquals(expectedDescription, foundTaskHistory.getNewDescription(), "La description de la tâche doit correspondre");
    }
}
