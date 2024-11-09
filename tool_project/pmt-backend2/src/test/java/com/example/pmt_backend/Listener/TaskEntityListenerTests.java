package com.example.pmt_backend.Listener;

import com.example.pmt_backend.listener.TaskEntityListener;
import com.example.pmt_backend.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class TaskEntityListenerTests {

    private TaskEntityListener taskEntityListener;
    private Task task;

    @BeforeEach
    public void setUp() {
        taskEntityListener = new TaskEntityListener();
        task = Mockito.mock(Task.class);

        // Définir le comportement du mock pour les méthodes appelées dans prePersist et preUpdate
        when(task.getCreatedAt()).thenReturn(null);
        when(task.getUpdatedAt()).thenReturn(null);
    }

    @Test
    public void testPrePersist() {
        // Appeler la méthode prePersist
        taskEntityListener.prePersist(task);

        // Vérifier que createdAt et updatedAt sont définis
        verify(task).setCreatedAt(any(LocalDateTime.class));
        verify(task).setUpdatedAt(any(LocalDateTime.class));
    }

    @Test
    public void testPreUpdate() {
        // Appeler la méthode preUpdate sans appeler task.setUpdatedAt() manuellement
        taskEntityListener.preUpdate(task);

        // Vérifier que updatedAt est mis à jour
        verify(task, times(1)).setUpdatedAt(any(LocalDateTime.class));
    }
}
