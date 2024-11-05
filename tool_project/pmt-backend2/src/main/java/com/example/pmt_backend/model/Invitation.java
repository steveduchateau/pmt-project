package com.example.pmt_backend.model;

import jakarta.persistence.*;

@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private Long projectId;
    
    // Ajout du champ role
    private String role;

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    // Ajout des getters et setters pour le rôle
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
