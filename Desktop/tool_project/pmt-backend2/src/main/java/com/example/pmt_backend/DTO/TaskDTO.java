package com.example.pmt_backend.DTO;

import java.time.LocalDate;

public class TaskDTO {
    private String name;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String assignedTo; // E-mail de la personne à qui la tâche est assignée
    private String projectName; // Ajout du nom du projet

    // Getters et Setters
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

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getProjectName() { // Getter pour le nom du projet
        return projectName;
    }

    public void setProjectName(String projectName) { // Setter pour le nom du projet
        this.projectName = projectName;
    }
}
