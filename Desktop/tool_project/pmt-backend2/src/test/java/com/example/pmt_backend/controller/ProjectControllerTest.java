package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProjectControllerTest {

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProjects() {
        // Données de test
        List<Project> projects = Collections.singletonList(new Project());
        when(projectService.getAllProjects()).thenReturn(projects);

        // Appel de la méthode à tester
        ResponseEntity<List<Project>> response = projectController.getAllProjects();

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projects, response.getBody());
    }
}
