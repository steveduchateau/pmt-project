package com.example.pmt_backend.service;

import com.example.pmt_backend.model.Invitation;
import com.example.pmt_backend.Repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class InvitationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private InvitationRepository invitationRepository;

    public void sendInvitation(String email, Long projectId, String role) {
        String subject = "Invitation à rejoindre le projet";
        String body = "Vous avez été invité à rejoindre le projet avec l'ID : " + projectId +
                      "\nRôle : " + role + "\n\n" +
                      "Pour accepter l'invitation, veuillez vous connecter à la plateforme.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void inviteMemberToProject(Invitation invitation) {
        // Enregistrer l'invitation dans la base de données
        invitationRepository.save(invitation);
        // Envoyer l'email d'invitation
        sendInvitation(invitation.getEmail(), invitation.getProjectId(), invitation.getRole());
    }
}
