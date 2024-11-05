package com.example.pmt_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth/invitations")
public class InvitationController {

    private List<Invitation> invitations = new ArrayList<>();

    // Récupérer toutes les invitations
    @GetMapping
    public List<Invitation> getAllInvitations() {
        return invitations;
    }

    // Créer une nouvelle invitation
    @PostMapping
    public ResponseEntity<String> createInvitation(@RequestBody Invitation invitation) {
        // Vérifier si l'invitation existe déjà
        for (Invitation existing : invitations) {
            if (existing.getEmail().equals(invitation.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Invitation déjà envoyée à cet email.");
            }
        }

        invitations.add(invitation);
        return ResponseEntity.status(HttpStatus.CREATED).body("Invitation envoyée avec succès.");
    }
}

// Classe pour représenter une invitation
class Invitation {
    private String email;
    private String role; // Ajouter d'autres champs si nécessaire

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
