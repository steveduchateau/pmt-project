package com.example.pmt_backend.DTO;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RoleDTOTest {

    @Test
    public void testRoleDTOGettersAndSetters() {
        RoleDTO roleDTO = new RoleDTO();
        
        // Test des setters
        roleDTO.setProjectId(1L);
        roleDTO.setUserId(2L);
        roleDTO.setRole("Developer");

        // Test des getters
        assertThat(roleDTO.getProjectId()).isEqualTo(1L);
        assertThat(roleDTO.getUserId()).isEqualTo(2L);
        assertThat(roleDTO.getRole()).isEqualTo("Developer");
    }
}
