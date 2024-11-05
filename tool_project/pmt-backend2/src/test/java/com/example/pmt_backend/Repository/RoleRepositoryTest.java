package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    private Role role;

    @BeforeEach
    public void setUp() {
        // Création d'une instance de test pour Role
        role = new Role();
        role.setName("ROLE_Member");
        
        // Enregistrement de l'instance dans la base de données de test
        role = roleRepository.save(role);
    }

    @Test
    public void testSaveRole() {
        // Vérifie que le rôle a été sauvegardé avec un ID généré
        assertThat(role.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        // Vérifie la récupération du rôle par ID
        Optional<Role> foundRole = roleRepository.findById(role.getId());
        assertThat(foundRole).isPresent();
        assertThat(foundRole.get().getName()).isEqualTo("ROLE_Member");
    }

    @Test
    public void testUpdateRole() {
        // Modification de l'entité Role et sauvegarde
        role.setName("ROLE_ADMIN");
        Role updatedRole = roleRepository.save(role);

        // Vérifie que les changements sont sauvegardés
        assertThat(updatedRole.getName()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    public void testDeleteRole() {
        // Suppression du rôle de la base de données
        roleRepository.delete(role);

        // Vérifie que le rôle n'est plus présent
        Optional<Role> deletedRole = roleRepository.findById(role.getId());
        assertThat(deletedRole).isNotPresent();
    }
}
