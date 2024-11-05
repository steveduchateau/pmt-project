package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.InviteMemberRequest;
import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.model.ProjectMember;
import com.example.pmt_backend.model.Invitation; 
import com.example.pmt_backend.model.User; 
import com.example.pmt_backend.service.ProjectMemberService;
import com.example.pmt_backend.service.InvitationService;
import com.example.pmt_backend.service.ProjectService;
import com.example.pmt_backend.service.UserService; 
import com.example.pmt_backend.service.EmailService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/auth/projects")
public class ProjectController {

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService; 

    @Autowired
    private EmailService emailService; 

    // Récupérer tous les projets
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    // Récupérer un projet par ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            return ResponseEntity.ok(project.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Créer un projet et ajouter automatiquement le créateur en tant qu'admin
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Long creatorUserId = project.getCreatorUserId();
        System.out.println("Création du projet avec l'ID de l'utilisateur créateur: " + creatorUserId);

        if (creatorUserId != null) {
            project.setAdminId(creatorUserId); 
        } else {
            System.err.println("L'ID de l'utilisateur créateur est null.");
            return ResponseEntity.badRequest().build(); 
        }

        // Enregistrer le projet
        Project savedProject = projectService.createProject(project);

        // Ajouter le créateur comme membre avec rôle admin
        ProjectMember creatorMember = new ProjectMember();
        creatorMember.setUserId(creatorUserId);
        creatorMember.setProjectId(savedProject.getId());
        creatorMember.setRole("admin");  
        projectMemberService.addMemberToProject(creatorMember);

        return ResponseEntity.ok(savedProject);
    }

    // Récupérer les membres d'un projet par ID de projet
    @GetMapping("/{projectId}/members")
    public ResponseEntity<List<ProjectMember>> getProjectMembers(@PathVariable Long projectId) {
        List<ProjectMember> members = projectMemberService.getMembersByProject(projectId);
        return ResponseEntity.ok(members);
    }

    // Ajouter un membre à un projet
    @PostMapping("/{projectId}/members")
    public ResponseEntity<ProjectMember> addProjectMember(@PathVariable Long projectId, @RequestBody ProjectMember member) {
        member.setProjectId(projectId);
        ProjectMember savedMember = projectMemberService.addMemberToProject(member);

        // Envoi de la notification par email
        sendEmailNotification(savedMember); // Envoi de l'email après ajout du membre

        return ResponseEntity.ok(savedMember);
    }

    // Fonction pour envoyer l'email de notification
    private void sendEmailNotification(ProjectMember member) {
        String email = member.getEmail();
        String subject = "Invitation à rejoindre le projet";
        String body = "Bonjour,\n\nVous avez été ajouté en tant que membre du projet avec le rôle: " + member.getRole() + ".\n\nCordialement,\nL'équipe de gestion de projet.";

        // Envoi de l'email
        emailService.sendEmail(email, subject, body); 
    }

    // Supprimer un membre d'un projet
    @DeleteMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long projectId, @PathVariable Long memberId) {
        try {
            projectMemberService.removeMember(memberId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // Récupérer tous les membres de tous les projets avec leurs rôles
    @GetMapping("/members")
    public ResponseEntity<List<ProjectMember>> getAllProjectMembers() {
    List<ProjectMember> members = projectMemberService.getAllProjectMembers();
    return ResponseEntity.ok(members);
}


    // Récupérer le rôle du membre actuel dans un projet
    @GetMapping("/{projectId}/members/{userId}/role")
    public ResponseEntity<String> getMemberRole(@PathVariable Long projectId, @PathVariable Long userId) {
        Optional<ProjectMember> member = projectMemberService.getMemberByProjectAndUserId(projectId, userId);
        return member.map(value -> ResponseEntity.ok(value.getRole()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Inviter un membre à un projet
    @PostMapping("/{projectId}/invite")
    public ResponseEntity<Map<String, String>> inviteMember(@PathVariable Long projectId, @RequestBody InviteMemberRequest inviteRequest) {
        try {
            Invitation invitation = new Invitation();
            invitation.setEmail(inviteRequest.getEmail());
            invitation.setProjectId(projectId);
            invitation.setRole(inviteRequest.getRole()); 

            invitationService.inviteMemberToProject(invitation); 

            User user = userService.findByEmail(inviteRequest.getEmail()); 

            ProjectMember invitedMember = new ProjectMember();
            invitedMember.setProjectId(projectId);
            invitedMember.setRole(inviteRequest.getRole());

            if (user != null) {
                invitedMember.setUserId(user.getId()); 
                invitedMember.setEmail(inviteRequest.getEmail()); 
                invitedMember.setIsAdmin(inviteRequest.getRole().equals("administrateur")); 
            } else {
                invitedMember.setEmail(inviteRequest.getEmail()); 
            }

            projectMemberService.addMemberToProject(invitedMember);

            return ResponseEntity.ok(Map.of("message", "Invitation envoyée avec succès et membre ajouté avec le rôle: " + inviteRequest.getRole()));
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'invitation : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Erreur lors de l'envoi de l'invitation"));
        }
    }

    // Récupérer le créateur d'un projet
    @GetMapping("/{projectId}/creator")
    public ResponseEntity<ProjectMember> getProjectCreator(@PathVariable Long projectId) {
        return projectService.getProjectCreator(projectId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Vérifier si l'utilisateur est l'administrateur du projet
    @GetMapping("/{projectId}/isAdmin/{userId}")
    public ResponseEntity<Boolean> isUserAdmin(@PathVariable Long projectId, @PathVariable Long userId) {
        boolean isAdmin = projectMemberService.isUserAdminInProject(projectId, userId);
        return ResponseEntity.ok(isAdmin);
    }
}
