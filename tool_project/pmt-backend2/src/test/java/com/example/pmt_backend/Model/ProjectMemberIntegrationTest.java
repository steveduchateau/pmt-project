package com.example.pmt_backend.Model;

import com.example.pmt_backend.Repository.ProjectMemberRepository;
import com.example.pmt_backend.Repository.ProjectRepository;
import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.model.ProjectMember;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProjectMemberIntegrationTest {

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setUp() {
        // Création d'un projet si aucun projet n'existe déjà
        if (projectRepository.count() == 0) {
            Project project = new Project();
            project.setName("Test Project");
            projectRepository.save(project);
        }
    }

    @Test
    public void testCreateAndRetrieveProjectMember() {
        Project project = projectRepository.findAll().get(0);

        ProjectMember projectMember = new ProjectMember();
        projectMember.setProjectId(project.getId());
        projectMember.setUserId(2L);
        projectMember.setRole("Observer");
        projectMember.setEmail("member@example.com");
        projectMember.setIsAdmin(false);

        ProjectMember savedProjectMember = projectMemberRepository.save(projectMember);

        assertThat(savedProjectMember).isNotNull();
        assertThat(savedProjectMember.getProjectId()).isEqualTo(project.getId());
        assertThat(savedProjectMember.getUserId()).isEqualTo(2L);
        assertThat(savedProjectMember.getRole()).isEqualTo("Observer");
        assertThat(savedProjectMember.getEmail()).isEqualTo("member@example.com");
        assertThat(savedProjectMember.isAdmin()).isFalse();

        ProjectMember retrievedProjectMember = projectMemberRepository.findById(savedProjectMember.getId()).orElse(null);
        assertThat(retrievedProjectMember).isNotNull();
        assertThat(retrievedProjectMember.getId()).isEqualTo(savedProjectMember.getId());
    }
}
