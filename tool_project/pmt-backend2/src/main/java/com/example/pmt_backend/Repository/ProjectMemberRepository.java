package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    // Trouver tous les membres par ID de projet
    List<ProjectMember> findByProjectId(Long projectId);

    // Trouver un membre de projet spécifique par ID de projet et ID d'utilisateur
    Optional<ProjectMember> findByProjectIdAndUserId(Long projectId, Long userId);

    // Trouver un membre par ID de projet et rôle
    Optional<ProjectMember> findByProjectIdAndRole(Long projectId, String role);
}
