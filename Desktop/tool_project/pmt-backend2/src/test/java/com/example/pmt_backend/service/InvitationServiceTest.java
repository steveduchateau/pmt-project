package com.example.pmt_backend.service;

import com.example.pmt_backend.model.Invitation;
import com.example.pmt_backend.Repository.InvitationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.verify;

public class InvitationServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private InvitationRepository invitationRepository;

    @InjectMocks
    private InvitationService invitationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
    }

    @Test
    public void testSendInvitation() {
        // Arrange
        String email = "test@example.com";
        Long projectId = 1L;
        String role = "Admin";

        // Act
        invitationService.sendInvitation(email, projectId, role);

        // Assert
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Invitation à rejoindre le projet");
        message.setText("Vous avez été invité à rejoindre le projet avec l'ID : " + projectId +
                        "\nRôle : " + role + "\n\n" +
                        "Pour accepter l'invitation, veuillez vous connecter à la plateforme.");

        verify(mailSender).send(message); // Vérifie que l'email a été envoyé
    }

    @Test
    public void testInviteMemberToProject() {
        // Arrange
        Invitation invitation = new Invitation();
        invitation.setEmail("member@example.com");
        invitation.setProjectId(1L);
        invitation.setRole("Observer");

        // Act
        invitationService.inviteMemberToProject(invitation);

        // Assert
        verify(invitationRepository).save(invitation); // Vérifie que l'invitation a été enregistrée
        verify(mailSender).send(org.mockito.ArgumentMatchers.any(SimpleMailMessage.class)); // Vérifie que l'email a été envoyé
    }
}
