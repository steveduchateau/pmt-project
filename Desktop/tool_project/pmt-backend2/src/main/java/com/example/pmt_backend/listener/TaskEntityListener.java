package com.example.pmt_backend.listener;

import com.example.pmt_backend.model.Task;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class TaskEntityListener {

    @PrePersist
    public void prePersist(Task task) {
        // Remplir les champs lors de la création
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        // Vous pouvez également définir le créateur ici si vous avez un moyen de l'obtenir
        // task.setCreatedBy("current_user_email"); // À remplacer par l'utilisateur actuel
    }

    @PreUpdate
    public void preUpdate(Task task) {
        // Mettre à jour le champ updatedAt lors de la mise à jour
        task.setUpdatedAt(LocalDateTime.now());
        // Vous pouvez également définir l'utilisateur qui a effectué la mise à jour ici
        // task.setUpdatedBy("current_user_email"); // À remplacer par l'utilisateur actuel
    }
}
