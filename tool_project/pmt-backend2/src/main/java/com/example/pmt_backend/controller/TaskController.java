package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.Task;
import com.example.pmt_backend.model.TaskHistory;
import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.service.EmailService;
import com.example.pmt_backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmailService emailService;

    // Récupérer toutes les tâches
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Récupérer une tâche par ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    // Récupérer toutes les tâches d'un projet
    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<Task>> getProjectTasks(@PathVariable Long projectId) {
        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    // Ajouter une tâche à un projet
    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Task> addTaskToProject(@PathVariable Long projectId, @RequestBody Task task) {
        task.setProject(new Project(projectId));
        String assignedByEmail = task.getAssignedBy();  // S'assurer que ce champ existe dans votre modèle
        task.setCreatedBy(assignedByEmail); // Mettre à jour created_by

        Task savedTask = taskService.addTask(task, assignedByEmail);

        // Envoyer un email à la personne assignée
        emailService.sendTaskNotification(task.getAssignedTo(), savedTask);
        
        return ResponseEntity.ok(savedTask);
    }

    // Modifier une tâche
    @PutMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Task taskDetails) {
        // Suppression de la mise à jour de updated_by
        Task updatedTask = taskService.updateTask(taskId, taskDetails);

        if (updatedTask != null) {
            emailService.sendTaskNotification(updatedTask.getAssignedTo(), updatedTask);
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }

    // Récupérer l'historique des modifications d'une tâche
    @GetMapping("/{taskId}/history")
    public ResponseEntity<List<TaskHistory>> getTaskHistory(@PathVariable Long taskId) {
        List<TaskHistory> history = taskService.getTaskHistory(taskId);

        if (!history.isEmpty()) {
            return ResponseEntity.ok(history);
        }

        // Retourner une liste vide si aucun historique n'existe
        return ResponseEntity.ok(new ArrayList<>());
    }

    // Méthode d'exemple
    public void someMethod(String inputString) {
        int stringLength = inputString.length();
        System.out.println("La longueur de la chaîne est : " + stringLength);
        for (char c : inputString.toCharArray()) {
            System.out.println(c);
        }
    }
}
