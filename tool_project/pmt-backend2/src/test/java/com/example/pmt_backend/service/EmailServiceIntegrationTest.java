package com.example.pmt_backend.service;

import static org.mockito.Mockito.verify;

import com.example.pmt_backend.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class EmailServiceIntegrationTest {

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private EmailService emailService;

    private Task task;

    @BeforeEach
    public void setUp() {
        // Création d'une tâche pour les tests
        task = new Task();
        task.setName("Test Task");
        task.setDescription("This is a test task.");
        task.setDueDate(LocalDate.of(2024, 12, 31)); // Utiliser LocalDate ici
        task.setPriority("High");
    }

    @Test
    public void testSendTaskNotification() {
        // Envoi de la notification de tâche
        emailService.sendTaskNotification("testuser@example.com", task);

        // Vérification que le message a été envoyé
        verify(emailSender).send(org.mockito.ArgumentMatchers.any(SimpleMailMessage.class));
    }

    @Test
    public void testSendProjectMemberNotification() {
        // Envoi de la notification de membre de projet
        emailService.sendProjectMemberNotification("testuser@example.com", 1L);

        // Vérification que le message a été envoyé
        verify(emailSender).send(org.mockito.ArgumentMatchers.any(SimpleMailMessage.class));
    }

    @Test
    public void testSendEmail() {
        // Envoi d'un e-mail générique
        emailService.sendEmail("testuser@example.com", "Test Subject", "This is a test email.");

        // Vérification que le message a été envoyé
        verify(emailSender).send(org.mockito.ArgumentMatchers.any(SimpleMailMessage.class));
    }
}
