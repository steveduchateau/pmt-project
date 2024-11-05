package com.example.pmt_backend.Model;



import org.junit.jupiter.api.Test;

import com.example.pmt_backend.model.ProjectMember;

import static org.junit.jupiter.api.Assertions.*;

class ProjectMemberTest {

    @Test
    void testProjectMemberGettersAndSetters() {
        // Création d'une instance de ProjectMember
        ProjectMember projectMember = new ProjectMember();

        // Test des setters
        projectMember.setId(1L);
        projectMember.setProjectId(100L);
        projectMember.setUserId(200L);
        projectMember.setRole("Admin");
        projectMember.setEmail("user@example.com");
        projectMember.setIsAdmin(true);

        // Test des getters
        assertEquals(1L, projectMember.getId());
        assertEquals(100L, projectMember.getProjectId());
        assertEquals(200L, projectMember.getUserId());
        assertEquals("Admin", projectMember.getRole());
        assertEquals("user@example.com", projectMember.getEmail());
        assertTrue(projectMember.isAdmin());
    }

    @Test
    void testProjectMemberDefaultConstructor() {
        // Création d'une instance de ProjectMember
        ProjectMember projectMember = new ProjectMember();

        // Vérification que les valeurs par défaut sont null ou false
        assertNull(projectMember.getId());
        assertNull(projectMember.getProjectId());
        assertNull(projectMember.getUserId());
        assertNull(projectMember.getRole());
        assertNull(projectMember.getEmail());
        assertFalse(projectMember.isAdmin());
    }
}
