package com.example.pmt_backend.controller;

import com.example.pmt_backend.model.Role;
import com.example.pmt_backend.Repository.RoleRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleControllerTest {

    @InjectMocks
    private RoleController roleController;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRoles() {
        // Données de test
        Role role = new Role();
        role.setName("Admin");
        when(roleRepository.findAll()).thenReturn(Collections.singletonList(role));

        // Appel de la méthode à tester
        List<Role> roles = roleController.getAllRoles();

        // Assertions
        assertEquals(1, roles.size());
        assertEquals("Admin", roles.get(0).getName());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void testCreateRole() {
        // Données de test
        Role role = new Role();
        role.setName("User");
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        // Appel de la méthode à tester
        Role createdRole = roleController.createRole(role);

        // Assertions
        assertEquals("User", createdRole.getName());
        verify(roleRepository, times(1)).save(role);
    }

    @SuppressWarnings({ })
    @Test
    void testUpdateRole() {
        // Données de test
        Long roleId = 1L;
        Role existingRole = new Role();
        existingRole.setName("User");
        
        Role updatedRoleDetails = new Role();
        updatedRoleDetails.setName("Admin");

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(existingRole));
        when(roleRepository.save(any(Role.class))).thenAnswer(invocation -> {
            Role roleToUpdate = invocation.getArgument(0);
            existingRole.setName(roleToUpdate.getName());
            return existingRole;
        });

        // Appel de la méthode à tester
        ResponseEntity<Role> response = roleController.updateRole(roleId, updatedRoleDetails);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Admin", response.getBody().getName()); // Vérifiez que le nom est maintenant "Admin"
        verify(roleRepository, times(1)).findById(roleId);
        verify(roleRepository, times(1)).save(existingRole);
    }

    @Test
    void testUpdateRoleNotFound() {
        // Données de test
        Long roleId = 1L;
        Role updatedRoleDetails = new Role();
        updatedRoleDetails.setName("Admin");

        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        // Appel de la méthode à tester
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            roleController.updateRole(roleId, updatedRoleDetails);
        });

        // Assertions
        assertEquals("Role not found with id 1", exception.getMessage());
        verify(roleRepository, times(1)).findById(roleId);
    }
}
