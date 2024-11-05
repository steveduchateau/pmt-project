package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId); // Méthode pour récupérer les tâches par ID de projet

    // Pour récupérer toutes les tâches avec pagination
    Page<Task> findByProjectId(Long projectId, Pageable pageable);

    // Pour récupérer les tâches par état (si vous avez un champ 'status' dans la classe Task)
    List<Task> findByProjectIdAndStatus(Long projectId, String status);
}
