package com.example.pmt_backend.service;

import com.example.pmt_backend.DTO.TaskDTO;
import com.example.pmt_backend.model.Task;
import com.example.pmt_backend.model.TaskHistory;
import com.example.pmt_backend.Repository.TaskRepository;
import com.example.pmt_backend.Repository.TaskHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

    /**
     * Ajoute une entrée dans l'historique des modifications pour la tâche donnée.
     * @param task La tâche pour laquelle l'historique est enregistré.
     * @param action L'action effectuée (ex : "Créée", "Modifiée", etc.).
     * @param modifiedBy L'e-mail de l'utilisateur ayant modifié la tâche.
     * @param oldDescription Ancienne description de la tâche.
     * @param newDescription Nouvelle description de la tâche.
     * @param oldStatus Ancien statut de la tâche.
     * @param newStatus Nouveau statut de la tâche.
     */
    private void addTaskHistory(Task task, String action, String modifiedBy, 
                                String oldDescription, String newDescription, 
                                String oldStatus, String newStatus) {
        TaskHistory history = new TaskHistory();
        history.setTask(task);
        history.setAction(action);
        history.setModifiedBy(modifiedBy != null ? modifiedBy : "system");
        history.setModifiedAt(LocalDateTime.now());
        history.setOldDescription(oldDescription);
        history.setNewDescription(newDescription);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);

        taskHistoryRepository.save(history);
        System.out.println("Historique de tâche ajouté pour l'action : " + action + " sur la tâche : " + task.getName());
    }

    /**
     * Récupère l'historique des modifications d'une tâche.
     * @param taskId L'ID de la tâche.
     * @return La liste des entrées d'historique pour la tâche.
     */
    public List<TaskHistory> getTaskHistory(Long taskId) {
        return taskHistoryRepository.findByTaskId(taskId);
    }

    /**
     * Crée une nouvelle tâche en utilisant un DTO, enregistre dans l'historique et retourne la tâche enregistrée.
     * @param taskDTO Les informations de la tâche sous forme de DTO.
     * @param assignedByEmail L'email de l'utilisateur assignant la tâche.
     * @return La tâche créée et enregistrée.
     */
    public Task createTask(TaskDTO taskDTO, String assignedByEmail) {
        Task newTask = new Task();
        newTask.setName(taskDTO.getName());
        newTask.setDescription(taskDTO.getDescription());
        newTask.setDueDate(taskDTO.getDueDate());
        newTask.setPriority(taskDTO.getPriority());
        newTask.setAssignedBy(assignedByEmail);
        newTask.setAssignedTo(taskDTO.getAssignedTo());
        Task savedTask = taskRepository.save(newTask);

        addTaskHistory(savedTask, "Créée", assignedByEmail, null, taskDTO.getDescription(), null, null); // Historique d'ajout

        return savedTask;
    }

    /**
     * Ajoute une tâche à la base de données, crée une entrée d'historique, et retourne la tâche enregistrée.
     * @param task La tâche à ajouter.
     * @param assignedByEmail L'email de l'utilisateur assignant la tâche.
     * @return La tâche ajoutée et enregistrée.
     */
    public Task addTask(Task task, String assignedByEmail) {
        task.setAssignedBy(assignedByEmail);
        Task savedTask = taskRepository.save(task);

        addTaskHistory(savedTask, "Créée", assignedByEmail, null, task.getDescription(), null, null); // Historique d'ajout

        return savedTask;
    }

    /**
     * Récupère toutes les tâches.
     * @return Une liste de toutes les tâches.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Récupère une tâche par son ID.
     * @param id L'ID de la tâche.
     * @return La tâche correspondante, ou null si elle n'existe pas.
     */
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    /**
     * Récupère toutes les tâches associées à un projet donné.
     * @param projectId L'ID du projet.
     * @return Une liste de tâches associées au projet.
     */
    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    /**
     * Met à jour une tâche existante et enregistre l'historique de modification.
     * @param id L'ID de la tâche à mettre à jour.
     * @param taskDetails Les nouvelles informations de la tâche.
     * @return La tâche mise à jour, ou null si elle n'existe pas.
     */
    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        if (task != null) {
            // Récupérer les anciennes valeurs
            String oldDescription = task.getDescription();
            String oldStatus = task.getStatus();

            // Mettre à jour la tâche
            task.setName(taskDetails.getName());
            task.setDescription(taskDetails.getDescription());
            task.setDueDate(taskDetails.getDueDate());
            task.setPriority(taskDetails.getPriority());
            task.setStatus(taskDetails.getStatus());
            task.setAssignedBy(taskDetails.getAssignedBy());
            task.setAssignedTo(taskDetails.getAssignedTo());
            
            Task updatedTask = taskRepository.save(task);
            addTaskHistory(updatedTask, "Modifiée", taskDetails.getAssignedBy(), 
                            oldDescription, taskDetails.getDescription(), 
                            oldStatus, taskDetails.getStatus()); // Historique de modification

            return updatedTask;
        }
        return null;
    }

}
