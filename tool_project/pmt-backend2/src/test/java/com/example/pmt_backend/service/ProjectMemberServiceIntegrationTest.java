package com.example.pmt_backend.service;

import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.model.ProjectMember;
import com.example.pmt_backend.Repository.ProjectMemberRepository;
import com.example.pmt_backend.Repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProjectMemberServiceIntegrationTest {

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setUp() {
        // Nettoyage des membres de projet avant chaque test
        projectMemberRepository.deleteAll();
        
        // Création d'un projet pour les tests
        if (projectRepository.count() == 0) {
            Project project = new Project();
            project.setName("Test Project");
            projectRepository.save(project);
        }
    }

    @Test
    public void testAddMemberToProject() {
        // Arrange
        Project project = projectRepository.findAll().get(0); // Récupère le projet existant
        ProjectMember member = new ProjectMember();
        member.setUserId(1L);
        member.setProjectId(project.getId());
        member.setRole("Member");

        // Act
        ProjectMember savedMember = projectMemberService.addMemberToProject(member);

        // Assert
        assertNotNull(savedMember);
        assertEquals(member.getUserId(), savedMember.getUserId());
        assertEquals(member.getProjectId(), savedMember.getProjectId());
        assertEquals(member.getRole(), savedMember.getRole());
    }

    @Test
    public void testGetMembersByProject() {
        // Arrange
        Project project = projectRepository.findAll().get(0);
        ProjectMember member1 = new ProjectMember();
        member1.setUserId(1L);
        member1.setProjectId(project.getId());
        member1.setRole("Member");
        projectMemberRepository.save(member1);

        ProjectMember member2 = new ProjectMember();
        member2.setUserId(2L);
        member2.setProjectId(project.getId());
        member2.setRole("Member");
        projectMemberRepository.save(member2);

        // Act
        List<ProjectMember> members = projectMemberService.getMembersByProject(project.getId());

        // Assert
        assertEquals(2, members.size()); // Attendu : 2 membres
    }

    @Test
    public void testUpdateMemberRole() {
        // Arrange
        Project project = projectRepository.findAll().get(0);
        ProjectMember member = new ProjectMember();
        member.setUserId(1L);
        member.setProjectId(project.getId());
        member.setRole("Member");
        ProjectMember savedMember = projectMemberRepository.save(member);

        // Act
        ProjectMember updatedMember = projectMemberService.updateMemberRole(savedMember.getId(), "Admin");

        // Assert
        assertEquals("Admin", updatedMember.getRole());
    }

    @Test
    public void testGetMemberByProjectAndUserId() {
        // Arrange
        Project project = projectRepository.findAll().get(0);
        ProjectMember member = new ProjectMember();
        member.setUserId(1L);
        member.setProjectId(project.getId());
        member.setRole("Member");
        projectMemberRepository.save(member);

        // Act
        Optional<ProjectMember> foundMember = projectMemberService.getMemberByProjectAndUserId(project.getId(), 1L);

        // Assert
        assertTrue(foundMember.isPresent());
        assertEquals(member.getId(), foundMember.get().getId());
    }

    @Test
    public void testIsUserAdminInProject() {
        // Arrange
        Project project = projectRepository.findAll().get(0);
        ProjectMember member = new ProjectMember();
        member.setUserId(1L);
        member.setProjectId(project.getId());
        member.setRole("admin");
        projectMemberRepository.save(member);

        // Act
        boolean isAdmin = projectMemberService.isUserAdminInProject(project.getId(), 1L);

        // Assert
        assertTrue(isAdmin);
    }
}
