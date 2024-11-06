package com.example.pmt_backend.Model;



import org.junit.jupiter.api.Test;

import com.example.pmt_backend.model.Role;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testRoleGettersAndSetters() {
        // Création d'une instance de Role
        Role role = new Role();

        // Test des setters
        role.setId(1L);
        role.setName("Admin");

        // Test des getters
        assertEquals(1L, role.getId());
        assertEquals("Admin", role.getName());
    }

    @Test
    void testRoleDefaultConstructor() {
        // Création d'une instance de Role
        Role role = new Role();

        // Vérification que les valeurs par défaut sont null
        assertNull(role.getId());
        assertNull(role.getName());
    }
}
