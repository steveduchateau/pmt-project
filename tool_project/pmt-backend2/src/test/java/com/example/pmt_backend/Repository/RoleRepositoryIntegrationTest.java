package com.example.pmt_backend.Repository;


import com.example.pmt_backend.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Rollback(false) // Permet de garder les données dans la base de données après chaque test
public class RoleRepositoryIntegrationTest {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        roleRepository.deleteAll(); // Nettoyage de la base de données avant chaque test
    }

    @Test
    public void testSaveAndFindRole() {
        // Préparer un rôle pour le test
        Role role = new Role();
        role.setName("ADMIN");

        // Sauvegarder le rôle
        Role savedRole = roleRepository.save(role);

        // Vérifier que le rôle est bien enregistré et peut être récupéré
        Optional<Role> foundRole = roleRepository.findById(savedRole.getId());
        assertThat(foundRole).isPresent();
        assertThat(foundRole.get().getName()).isEqualTo("ADMIN");
    }

    @Test
    public void testDeleteRole() {
        // Préparer un rôle pour le test
        Role role = new Role();
        role.setName("Member");

        // Sauvegarder le rôle
        roleRepository.save(role);

        // Vérifier que le rôle existe avant la suppression
        Optional<Role> foundRoleBeforeDelete = roleRepository.findById(role.getId());
        assertThat(foundRoleBeforeDelete).isPresent();

        // Supprimer le rôle
        roleRepository.delete(role);

        // Vérifier que le rôle n'existe plus après la suppression
        Optional<Role> foundRoleAfterDelete = roleRepository.findById(role.getId());
        assertThat(foundRoleAfterDelete).isNotPresent();
    }
}
