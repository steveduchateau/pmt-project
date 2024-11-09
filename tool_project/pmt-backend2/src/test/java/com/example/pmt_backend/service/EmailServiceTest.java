package com.example.pmt_backend.service;

import com.example.pmt_backend.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

public class EmailServiceTest {

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
    }

    @Test
    public void testSendTaskNotification() {
        // Arrange
        String to = "test@example.com";
        Task task = new Task();
        task.setName("Test Task");
        task.setDescription("Test Task Description");
        task.setDueDate(LocalDate.parse("2024-12-31")); // Utilisation de LocalDate
        task.setPriority("High");

        // Act
        emailService.sendTaskNotification(to, task);

        // Assert
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Nouvelle tâche assignée : " + task.getName());
        message.setText("Vous avez été assigné à la tâche suivante :\n\n" +
                "Nom : " + task.getName() + "\n" +
                "Description : " + task.getDescription() + "\n" +
                "Date d'échéance : " + task.getDueDate() + "\n" +
                "Priorité : " + task.getPriority());

        verify(emailSender).send(message);
    }

    @Test
    public void testSendProjectMemberNotification() {
        // Arrange
        String to = "member@example.com";
        Long projectId = 1L;

        // Act
        emailService.sendProjectMemberNotification(to, projectId);

        // Assert
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Vous avez été ajouté à un projet");
        message.setText("Bonjour,\n\nVous avez été ajouté au projet avec l'ID: " + projectId + ".\n\nCordialement.");

        verify(emailSender).send(message);
    }

    @Test
    public void testSendEmail() {
        // Arrange
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        // Act
        emailService.sendEmail(to, subject, body);

        // Assert
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        verify(emailSender).send(message);
    }
}
