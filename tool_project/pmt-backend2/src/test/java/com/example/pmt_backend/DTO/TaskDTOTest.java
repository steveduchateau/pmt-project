package com.example.pmt_backend.DTO;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskDTOTest {

    @Test
    public void testTaskDTOGettersAndSetters() {
        TaskDTO taskDTO = new TaskDTO();

        // Test des setters
        taskDTO.setName("Sample Task");
        taskDTO.setDescription("This is a sample task.");
        taskDTO.setDueDate(LocalDate.of(2024, 12, 31));
        taskDTO.setPriority("High");
        taskDTO.setAssignedTo("user@example.com");
        taskDTO.setProjectName("Sample Project");

        // Test des getters
        assertThat(taskDTO.getName()).isEqualTo("Sample Task");
        assertThat(taskDTO.getDescription()).isEqualTo("This is a sample task.");
        assertThat(taskDTO.getDueDate()).isEqualTo(LocalDate.of(2024, 12, 31));
        assertThat(taskDTO.getPriority()).isEqualTo("High");
        assertThat(taskDTO.getAssignedTo()).isEqualTo("user@example.com");
        assertThat(taskDTO.getProjectName()).isEqualTo("Sample Project");
    }
}
