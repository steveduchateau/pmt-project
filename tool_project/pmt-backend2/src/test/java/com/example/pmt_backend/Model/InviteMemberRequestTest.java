package com.example.pmt_backend.Model;

import org.junit.jupiter.api.Test;

import com.example.pmt_backend.model.InviteMemberRequest;

import static org.junit.jupiter.api.Assertions.*;

class InviteMemberRequestTest {

    @Test
    void testInviteMemberRequestGettersAndSetters() {
        // Création d'une instance d'InviteMemberRequest
        InviteMemberRequest inviteRequest = new InviteMemberRequest();

        // Test des setters
        inviteRequest.setUserId(1L);
        inviteRequest.setEmail("user@example.com");
        inviteRequest.setProjectId(100L);
        inviteRequest.setRole("Member");

        // Test des getters
        assertEquals(1L, inviteRequest.getUserId());
        assertEquals("user@example.com", inviteRequest.getEmail());
        assertEquals(100L, inviteRequest.getProjectId());
        assertEquals("Member", inviteRequest.getRole());
    }

    @Test
    void testInviteMemberRequestDefaultConstructor() {
        // Création d'une instance d'InviteMemberRequest
        InviteMemberRequest inviteRequest = new InviteMemberRequest();

        // Vérification que les valeurs par défaut sont null
        assertNull(inviteRequest.getUserId());
        assertNull(inviteRequest.getEmail());
        assertNull(inviteRequest.getProjectId());
        assertNull(inviteRequest.getRole());
    }
}
