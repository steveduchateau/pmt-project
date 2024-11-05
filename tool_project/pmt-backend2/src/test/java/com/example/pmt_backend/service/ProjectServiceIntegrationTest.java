package com.example.pmt_backend.service;

import com.example.pmt_backend.model.Project;
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
public class ProjectServiceIntegrationTest {

    @Autowired
    private ProjectService projectService;

    private Project testProject;

    @BeforeEach
    public void setUp() {
        testProject = new Project();
        testProject.setName("Test Project");
        testProject.setDescription("Description of Test Project");
    }

    @Test
    public void testCreateProject() {
        Project createdProject = projectService.createProject(testProject);
        assertNotNull(createdProject.getId());
        assertEquals(testProject.getName(), createdProject.getName());
    }

    @Test
    public void testGetAllProjects() {
        projectService.createProject(testProject);
        List<Project> projects = projectService.getAllProjects();

        // Vérifie que le projet récemment ajouté est bien présent
        assertTrue(projects.stream().anyMatch(project -> project.getName().equals("Test Project")));
    }

    @Test
    public void testGetProjectById() {
        Project createdProject = projectService.createProject(testProject);
        Optional<Project> foundProject = projectService.getProjectById(createdProject.getId());
        assertTrue(foundProject.isPresent());
        assertEquals(createdProject.getId(), foundProject.get().getId());
    }
}
