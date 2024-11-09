package com.example.pmt_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_history")
public class TaskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id") // Création de la relation Many-to-One avec Task
    private Task task; // Lien vers l'objet Task

    private String action; // Action effectuée (ex: "CREATED", "UPDATED", "DELETED")
    private String modifiedBy; // E-mail de l'utilisateur qui a effectué la modification
    private LocalDateTime modifiedAt; // Date et heure de la modification
    private String oldDescription; // Ancienne description de la tâche
    private String newDescription; // Nouvelle description de la tâche
    private String oldStatus; // Ancien statut de la tâche
    private String newStatus; // Nouveau statut de la tâche

    // Nouveaux champs
    @Column(name = "created_at")
    private LocalDateTime createdAt; // Utilisation de LocalDateTime pour l'horodatage

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Utilisation de LocalDateTime pour l'horodatage

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getOldDescription() {
        return oldDescription;
    }

    public void setOldDescription(String oldDescription) {
        this.oldDescription = oldDescription;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    // Getters et Setters pour les nouveaux champs
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
