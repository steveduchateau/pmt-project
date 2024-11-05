
package com.example.pmt_backend.Model;

import com.example.pmt_backend.Repository.InvitationRepository;
import com.example.pmt_backend.model.Invitation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class InvitationIntegrationTest {

    @Autowired
    private InvitationRepository invitationRepository; // Assurez-vous d'avoir un repository pour Invitation

    @Test
    public void testCreateAndRetrieveInvitation() {
        // Création d'une nouvelle invitation
        Invitation invitation = new Invitation();
        invitation.setEmail("test@example.com");
        invitation.setProjectId(1L);
        invitation.setRole("Admin");

        // Sauvegarde de l'invitation
        Invitation savedInvitation = invitationRepository.save(invitation);

        // Vérification que l'invitation a été sauvegardée correctement
        assertThat(savedInvitation).isNotNull();
        assertThat(savedInvitation.getId()).isNotNull(); // Vérifie que l'ID a été généré
        assertThat(savedInvitation.getEmail()).isEqualTo("test@example.com");
        assertThat(savedInvitation.getProjectId()).isEqualTo(1L);
        assertThat(savedInvitation.getRole()).isEqualTo("Admin");

        // Récupération de l'invitation par ID
        Invitation retrievedInvitation = invitationRepository.findById(savedInvitation.getId()).orElse(null);

        // Vérification que l'invitation récupérée est la même que celle sauvegardée
        assertThat(retrievedInvitation).isNotNull();
        assertThat(retrievedInvitation.getId()).isEqualTo(savedInvitation.getId());
        assertThat(retrievedInvitation.getEmail()).isEqualTo(savedInvitation.getEmail());
        assertThat(retrievedInvitation.getProjectId()).isEqualTo(savedInvitation.getProjectId());
        assertThat(retrievedInvitation.getRole()).isEqualTo(savedInvitation.getRole());
    }
}
