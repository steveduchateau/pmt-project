package com.example.pmt_backend.DTO;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class ProjectDTOTest {

    @Test
    public void testProjectDTOGettersAndSetters() {
        ProjectDTO projectDTO = new ProjectDTO();
        
        // Test des setters
        projectDTO.setName("Project Name");
        projectDTO.setDescription("Project Description");
        LocalDate startDate = LocalDate.now();
        projectDTO.setStartDate(startDate);

        // Test des getters
        assertThat(projectDTO.getName()).isEqualTo("Project Name");
        assertThat(projectDTO.getDescription()).isEqualTo("Project Description");
        assertThat(projectDTO.getStartDate()).isEqualTo(startDate);
    }

    @Test
    public void testProjectDTOConstructor() {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("Project Name");
        projectDTO.setDescription("Project Description");
        LocalDate startDate = LocalDate.now();
        projectDTO.setStartDate(startDate);

        assertThat(projectDTO.getName()).isEqualTo("Project Name");
        assertThat(projectDTO.getDescription()).isEqualTo("Project Description");
        assertThat(projectDTO.getStartDate()).isEqualTo(startDate);
    }
}
