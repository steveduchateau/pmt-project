package com.example.pmt_backend.Model;

import org.junit.jupiter.api.Test;

import com.example.pmt_backend.model.Invitation;

import static org.junit.jupiter.api.Assertions.*;

class InvitationTest {

    @Test
    void testInvitationGettersAndSetters() {
        // Création d'une instance d'Invitation
        Invitation invitation = new Invitation();

        // Test des setters
        invitation.setId(1L);
        invitation.setEmail("user@example.com");
        invitation.setProjectId(100L);
        invitation.setRole("Admin");

        // Test des getters
        assertEquals(1L, invitation.getId());
        assertEquals("user@example.com", invitation.getEmail());
        assertEquals(100L, invitation.getProjectId());
        assertEquals("Admin", invitation.getRole());
    }

    @Test
    void testInvitationDefaultConstructor() {
        // Création d'une instance d'Invitation
        Invitation invitation = new Invitation();

        // Vérification que les valeurs par défaut sont null
        assertNull(invitation.getId());
        assertNull(invitation.getEmail());
        assertNull(invitation.getProjectId());
        assertNull(invitation.getRole());
    }
}
