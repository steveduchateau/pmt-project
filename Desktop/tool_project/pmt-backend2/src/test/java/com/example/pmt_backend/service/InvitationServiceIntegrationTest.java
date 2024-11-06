package com.example.pmt_backend.service;

import com.example.pmt_backend.model.Invitation;
import com.example.pmt_backend.Repository.InvitationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InvitationServiceIntegrationTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private InvitationRepository invitationRepository;

    @InjectMocks
    private InvitationService invitationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInviteMemberToProject() {
        // Arrange
        Invitation invitation = new Invitation();
        invitation.setEmail("test@example.com");
        invitation.setProjectId(1L);
        invitation.setRole("Member");

        // Act
        invitationService.inviteMemberToProject(invitation);

        // Assert: Vérifier que l'invitation a été enregistrée dans le repository
        verify(invitationRepository, times(1)).save(invitation);

        // Assert: Vérifier que l'e-mail d'invitation a été envoyé
        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(messageCaptor.capture());

        // Vérifier le contenu de l'e-mail
        SimpleMailMessage sentMessage = messageCaptor.getValue();
        assertEquals("Invitation à rejoindre le projet", sentMessage.getSubject());
        assertEquals("Vous avez été invité à rejoindre le projet avec l'ID : 1\nRôle : Member\n\nPour accepter l'invitation, veuillez vous connecter à la plateforme.", sentMessage.getText());
        assertEquals("test@example.com", sentMessage.getTo()[0]);
    }

    @Test
    public void testSendInvitation() {
        // Arrange
        String email = "test@example.com";
        Long projectId = 1L;
        String role = "Member";

        // Act
        invitationService.sendInvitation(email, projectId, role);

        // Assert: Vérifier que l'e-mail d'invitation a été envoyé
        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(messageCaptor.capture());

        // Vérifier le contenu de l'e-mail
        SimpleMailMessage sentMessage = messageCaptor.getValue();
        assertEquals("Invitation à rejoindre le projet", sentMessage.getSubject());
        assertEquals("Vous avez été invité à rejoindre le projet avec l'ID : 1\nRôle : Member\n\nPour accepter l'invitation, veuillez vous connecter à la plateforme.", sentMessage.getText());
        assertEquals("test@example.com", sentMessage.getTo()[0]);
    }
}
