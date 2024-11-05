package com.example.pmt_backend.Model;
import com.example.pmt_backend.Repository.RoleRepository;
import com.example.pmt_backend.model.Role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoleIntegrationTest {

    @Autowired
    private RoleRepository roleRepository; // Repository de Role

    @Test
    public void testCreateAndRetrieveRole() {
        // Création d'un nouveau rôle
        Role role = new Role();
        role.setName("Admin");

        // Sauvegarde du rôle
        Role savedRole = roleRepository.save(role);

        // Vérification que le rôle a été sauvegardé correctement
        assertThat(savedRole).isNotNull();
        assertThat(savedRole.getId()).isNotNull(); // Vérifie que l'ID a été généré
        assertThat(savedRole.getName()).isEqualTo("Admin");

        // Récupération du rôle par ID pour vérification
        Role retrievedRole = roleRepository.findById(savedRole.getId()).orElse(null);

        // Vérification que le rôle récupéré est le même que celui sauvegardé
        assertThat(retrievedRole).isNotNull();
        assertThat(retrievedRole.getId()).isEqualTo(savedRole.getId());
        assertThat(retrievedRole.getName()).isEqualTo(savedRole.getName());
    }
}
