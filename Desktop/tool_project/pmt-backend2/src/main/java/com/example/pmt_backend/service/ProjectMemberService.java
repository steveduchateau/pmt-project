// ProjectMemberService.java
package com.example.pmt_backend.service;

import com.example.pmt_backend.model.ProjectMember;
import com.example.pmt_backend.Repository.ProjectMemberRepository;  // Corriger le nom du package
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;

    public ProjectMemberService(ProjectMemberRepository projectMemberRepository) {
        this.projectMemberRepository = projectMemberRepository;
    }

    // Récupérer les membres d'un projet
    public List<ProjectMember> getMembersByProject(Long projectId) {
        return projectMemberRepository.findByProjectId(projectId);
    }

    // Ajouter un membre à un projet
    public ProjectMember addMemberToProject(ProjectMember member) {
        return projectMemberRepository.save(member);
    }

    // Modifier le rôle d'un membre du projet
    public ProjectMember updateMemberRole(Long memberId, String newRole) {
        return projectMemberRepository.findById(memberId).map(member -> {
            member.setRole(newRole);
            return projectMemberRepository.save(member);
        }).orElseThrow(() -> new RuntimeException("Membre non trouvé"));
    }

    // Supprimer un membre
    public void removeMember(Long memberId) {
        projectMemberRepository.deleteById(memberId);
    }

    public List<ProjectMember> getAllProjectMembers() {
        return projectMemberRepository.findAll(); // Assurez-vous que votre repository a une méthode findAll()
    }
    
    // Récupérer un membre par ID de projet et ID d'utilisateur
    public Optional<ProjectMember> getMemberByProjectAndUserId(Long projectId, Long userId) {
        return projectMemberRepository.findByProjectIdAndUserId(projectId, userId);
    }

    // Vérifier si un utilisateur est administrateur d'un projet
    public boolean isUserAdminInProject(Long projectId, Long userId) {
        Optional<ProjectMember> member = projectMemberRepository.findByProjectIdAndUserId(projectId, userId);
        return member.isPresent() && "admin".equals(member.get().getRole());
    }
}
