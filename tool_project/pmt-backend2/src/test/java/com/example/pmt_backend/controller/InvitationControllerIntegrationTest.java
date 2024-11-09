package com.example.pmt_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InvitationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Réinitialiser l'état avant chaque test (si nécessaire)
    }

    @Test
    public void testCreateInvitation() throws Exception {
        Invitation invitation = new Invitation();
        invitation.setEmail("unique_test1@example.com"); // Utiliser une adresse unique
        invitation.setRole("Member");

        mockMvc.perform(post("/auth/invitations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invitation)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Invitation envoyée avec succès."));
    }

    @Test
    public void testGetAllInvitations() throws Exception {
        // D'abord, créer une invitation
        Invitation invitation = new Invitation();
        invitation.setEmail("unique_test2@example.com"); // Utiliser une adresse unique
        invitation.setRole("Member");
        mockMvc.perform(post("/auth/invitations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invitation)));

        // Ensuite, récupérer toutes les invitations
        mockMvc.perform(get("/auth/invitations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"email\":\"unique_test2@example.com\",\"role\":\"Member\"}]")); // Vérifier que l'invitation est présente
    }

    @Test
    public void testCreateInvitationAlreadyExists() throws Exception {
        Invitation invitation = new Invitation();
        invitation.setEmail("unique_test3@example.com"); // Utiliser une adresse unique
        invitation.setRole("Member");

        // Créer une invitation
        mockMvc.perform(post("/auth/invitations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invitation)));

        // Essayer de créer la même invitation
        mockMvc.perform(post("/auth/invitations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invitation)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Invitation déjà envoyée à cet email."));
    }
}
