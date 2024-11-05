package com.example.pmt_backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvitationControllerTest {

    private InvitationController invitationController;

    @BeforeEach
    void setUp() {
        invitationController = new InvitationController();
    }

    @Test
    void testGetAllInvitationsInitiallyEmpty() {
        List<Invitation> invitations = invitationController.getAllInvitations();
        assertTrue(invitations.isEmpty(), "La liste des invitations devrait être vide au départ.");
    }

    @Test
    void testCreateInvitationSuccessfully() {
        Invitation invitation = new Invitation();
        invitation.setEmail("test@example.com");
        invitation.setRole("Admin");

        ResponseEntity<String> response = invitationController.createInvitation(invitation);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Invitation envoyée avec succès.", response.getBody());

        List<Invitation> invitations = invitationController.getAllInvitations();
        assertEquals(1, invitations.size());
        assertEquals("test@example.com", invitations.get(0).getEmail());
    }

    @Test
    void testCreateDuplicateInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setEmail("duplicate@example.com");
        invitation1.setRole("Admin");

        Invitation invitation2 = new Invitation();
        invitation2.setEmail("duplicate@example.com");
        invitation2.setRole("ADMIN");

        // Ajouter la première invitation
        invitationController.createInvitation(invitation1);

        // Essayer d'ajouter une invitation avec le même email
        ResponseEntity<String> response = invitationController.createInvitation(invitation2);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Invitation déjà envoyée à cet email.", response.getBody());

        List<Invitation> invitations = invitationController.getAllInvitations();
        assertEquals(1, invitations.size(), "Il ne devrait y avoir qu'une seule invitation.");
        assertEquals("duplicate@example.com", invitations.get(0).getEmail());
    }
}
