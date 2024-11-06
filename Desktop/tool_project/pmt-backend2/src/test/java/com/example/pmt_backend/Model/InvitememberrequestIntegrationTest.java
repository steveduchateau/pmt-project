package com.example.pmt_backend.Model;

import com.example.pmt_backend.Repository.InvitationRepository;
import com.example.pmt_backend.model.Invitation;
import com.example.pmt_backend.model.InviteMemberRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InvitememberrequestIntegrationTest {

    @Autowired
    private InvitationRepository invitationRepository; // Repository d'invitation

    @Test
    public void testInviteMemberRequest() {
        // Création d'une nouvelle demande d'invitation
        InviteMemberRequest request = new InviteMemberRequest();
        request.setEmail("member@example.com");
        request.setProjectId(1L);
        request.setRole("Member");

        // Convertir InviteMemberRequest en Invitation
        Invitation invitation = new Invitation();
        invitation.setEmail(request.getEmail());
        invitation.setProjectId(request.getProjectId());
        invitation.setRole(request.getRole());

        // Sauvegarde de l'invitation
        Invitation savedInvitation = invitationRepository.save(invitation);

        // Vérification que l'invitation a été sauvegardée correctement
        assertThat(savedInvitation).isNotNull();
        assertThat(savedInvitation.getId()).isNotNull(); // Vérifie que l'ID a été généré
        assertThat(savedInvitation.getEmail()).isEqualTo("member@example.com");
        assertThat(savedInvitation.getProjectId()).isEqualTo(1L);
        assertThat(savedInvitation.getRole()).isEqualTo("Member");

        // Récupération de l'invitation par ID pour vérification
        Invitation retrievedInvitation = invitationRepository.findById(savedInvitation.getId()).orElse(null);

        // Vérification que l'invitation récupérée est la même que celle sauvegardée
        assertThat(retrievedInvitation).isNotNull();
        assertThat(retrievedInvitation.getId()).isEqualTo(savedInvitation.getId());
        assertThat(retrievedInvitation.getEmail()).isEqualTo(savedInvitation.getEmail());
        assertThat(retrievedInvitation.getProjectId()).isEqualTo(savedInvitation.getProjectId());
        assertThat(retrievedInvitation.getRole()).isEqualTo(savedInvitation.getRole());
    }
}
