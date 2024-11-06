package com.example.pmt_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.example.pmt_backend.listener.TaskEntityListener;

@Entity
@Table(name = "tasks")
@EntityListeners(TaskEntityListener.class) // Enregistre le listener pour gérer les événements de cycle de vie de l'entité
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate dueDate;
    private String priority; // Ex: HIGH, MEDIUM, LOW
    private String assignedBy; // E-mail de l'utilisateur qui assigne la tâche
    private String assignedTo; // E-mail de la personne à qui la tâche est assignée

    @ManyToMany
    @JoinTable(
        name = "task_users",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> assignedUsers;

    private String status; // Statut de la tâche (ex: "IN_PROGRESS", "COMPLETED")

    @ManyToOne
    @JoinColumn(name = "project_id") // Relation Many-to-One avec Project
    @JsonBackReference // Annotation pour éviter la récursivité
    private Project project;

    // Champ pour stocker l'ID du projet directement
    @Column(name = "project_id", insertable = false, updatable = false)
    private Long projectId;

    // Nouveaux champs
    @Column(name = "created_by")
    private String createdBy;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        this.projectId = (project != null) ? project.getId() : null; // Met à jour projectId lorsque le projet est défini
    }

    // Méthode pour obtenir le nom du projet
    public String getProjectName() {
        return project != null ? project.getName() : null; // Retourne le nom du projet ou null si le projet est absent
    }

    // Getters et Setters pour les nouveaux champs
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

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

    public Long getProjectId() {
        return projectId; // Retourne l'ID du projet
    }
}
