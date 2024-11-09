package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.Invitation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // Indique que ce test est un test d'intégration JPA
@ActiveProfiles("test") // Utilisez le profil test
public class InvitationRepositoryIntegrationTest {

    @Autowired
    private InvitationRepository invitationRepository;

    @BeforeEach
    public void setUp() {
        invitationRepository.deleteAll(); // Assurez-vous que la base de données est vide avant chaque test
    }

    @Test
    public void testSaveAndExistsByEmail() {
        // Création d'une nouvelle invitation
        Invitation invitation = new Invitation();
        invitation.setEmail("invitee123@example.com"); // Adresse email modifiée
        // invitation.setMessage("You're invited!"); // Supprimez cette ligne

        // Enregistrement de l'invitation dans la base de données
        invitationRepository.save(invitation);

        // Vérifiez que l'invitation existe par email
        boolean exists = invitationRepository.existsByEmail("invitee123@example.com");

        // Assertions
        assertThat(exists).isTrue(); // Vérifiez que l'invitation existe
    }

    @Test
    public void testExistsByEmail_ShouldReturnFalse_WhenEmailDoesNotExist() {
        // Vérifiez qu'aucune invitation n'existe pour cet email
        boolean exists = invitationRepository.existsByEmail("nonexistent@example.com");

        // Assertions
        assertThat(exists).isFalse(); // Vérifiez que l'invitation n'existe pas
    }
}
