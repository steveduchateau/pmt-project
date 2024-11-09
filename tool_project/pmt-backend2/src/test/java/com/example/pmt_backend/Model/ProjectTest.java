package com.example.pmt_backend.Model;


import org.junit.jupiter.api.Test;

import com.example.pmt_backend.model.Project;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void testProjectGettersAndSetters() {
        // Création d'une instance de Project
        Project project = new Project();

        // Test des setters
        project.setId(1L);
        project.setName("Project A");
        project.setDescription("Description of Project A");
        project.setStartDate(LocalDate.of(2023, 1, 1));
        project.setCreatorUserId(10L);
        project.setCreatorEmail("creator@example.com");
        project.setAdminId(10L);

        // Test des getters
        assertEquals(1L, project.getId());
        assertEquals("Project A", project.getName());
        assertEquals("Description of Project A", project.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), project.getStartDate());
        assertEquals(10L, project.getCreatorUserId());
        assertEquals("creator@example.com", project.getCreatorEmail());
        assertEquals(10L, project.getAdminId());
    }

    @Test
    void testProjectConstructorWithId() {
        // Création d'une instance de Project avec un ID
        Project project = new Project(1L);

        // Vérification que l'ID est bien initialisé
        assertEquals(1L, project.getId());
    }

    @Test
    void testProjectDefaultConstructor() {
        // Création d'une instance de Project
        Project project = new Project();

        // Vérification que les valeurs par défaut sont null
        assertNull(project.getId());
        assertNull(project.getName());
        assertNull(project.getDescription());
        assertNull(project.getStartDate());
        assertNull(project.getCreatorUserId());
        assertNull(project.getCreatorEmail());
        assertNull(project.getAdminId());
    }
}
