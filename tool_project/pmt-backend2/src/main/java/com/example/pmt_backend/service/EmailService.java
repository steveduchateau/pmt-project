package com.example.pmt_backend.service;

import com.example.pmt_backend.model.Task; // Ajoutez cette importation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    // Méthode existante pour les notifications de tâches
    public void sendTaskNotification(String to, Task task) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Nouvelle tâche assignée : " + task.getName());
        message.setText("Vous avez été assigné à la tâche suivante :\n\n" +
                        "Nom : " + task.getName() + "\n" +
                        "Description : " + task.getDescription() + "\n" +
                        "Date d'échéance : " + task.getDueDate() + "\n" +
                        "Priorité : " + task.getPriority());

        emailSender.send(message);
    }

    // Nouvelle méthode pour envoyer un email lors de l'ajout d'un membre au projet
    public void sendProjectMemberNotification(String to, Long projectId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Vous avez été ajouté à un projet");
        message.setText("Bonjour,\n\nVous avez été ajouté au projet avec l'ID: " + projectId + ".\n\nCordialement.");
        
        emailSender.send(message);
    }

    // Nouvelle méthode pour envoyer un email générique
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
    }
}
