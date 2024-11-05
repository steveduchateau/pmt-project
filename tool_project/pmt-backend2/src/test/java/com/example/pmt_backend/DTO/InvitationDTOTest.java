package com.example.pmt_backend.DTO;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class InvitationDTOTest {

    @Test
    public void testInvitationDTOGettersAndSetters() {
        InvitationDTO invitationDTO = new InvitationDTO();
        
        // Test des setters
        invitationDTO.setEmail("testuser@example.com");
        invitationDTO.setProjectId(1L);

        // Test des getters
        assertThat(invitationDTO.getEmail()).isEqualTo("testuser@example.com");
        assertThat(invitationDTO.getProjectId()).isEqualTo(1L);
    }

    @Test
    public void testInvitationDTOConstructor() {
        InvitationDTO invitationDTO = new InvitationDTO();
        invitationDTO.setEmail("testuser@example.com");
        invitationDTO.setProjectId(1L);

        assertThat(invitationDTO.getEmail()).isEqualTo("testuser@example.com");
        assertThat(invitationDTO.getProjectId()).isEqualTo(1L);
    }
}
