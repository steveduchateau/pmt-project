package com.example.pmt_backend.model;

public class InviteMemberRequest {
    private Long userId;  // Ajoutez cet attribut
    private String email;
    private Long projectId;  // Ajoutez cet attribut
    private String role; // Rôle spécifié

    // Getters et Setters
    public Long getUserId() {  // Ajoutez le getter pour userId
        return userId;
    }

    public void setUserId(Long userId) {  // Ajoutez le setter pour userId
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getProjectId() {  // Ajoutez le getter pour projectId
        return projectId;
    }

    public void setProjectId(Long projectId) {  // Ajoutez le setter pour projectId
        this.projectId = projectId;
    }

    public String getRole() {  // Ajoutez le getter pour le rôle
        return role;
    }

    public void setRole(String role) {  // Ajoutez le setter pour le rôle
        this.role = role;
    }
}
