package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.ProjectMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProjectMemberRepositoryTest {

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    private ProjectMember member1;
    private ProjectMember member2;

    @BeforeEach
    public void setUp() {
        // Nettoyage de la base de données de test pour éviter des données résiduelles
        projectMemberRepository.deleteAll();

        // Création d'instances de test pour ProjectMember
        member1 = new ProjectMember();
        member1.setProjectId(1L);
        member1.setUserId(100L);
        member1.setRole("Admin");

        member2 = new ProjectMember();
        member2.setProjectId(1L);
        member2.setUserId(101L);
        member2.setRole("Observer");

        // Enregistrement des instances dans la base de données de test
        projectMemberRepository.save(member1);
        projectMemberRepository.save(member2);
    }

    @Test
    public void testFindByProjectId() {
        // Tester la récupération des membres par ID de projet
        List<ProjectMember> members = projectMemberRepository.findByProjectId(1L);
        assertThat(members).hasSize(2); // Vérifier qu'il y a deux membres pour le projet avec ID 1
    }

    @Test
    public void testFindByProjectIdAndUserId() {
        // Tester la récupération d'un membre spécifique par ID de projet et ID d'utilisateur
        Optional<ProjectMember> foundMember = projectMemberRepository.findByProjectIdAndUserId(1L, 100L);
        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getUserId()).isEqualTo(100L);
        assertThat(foundMember.get().getRole()).isEqualTo("Admin");
    }

    @Test
    public void testFindByProjectIdAndRole() {
        // Tester la récupération d'un membre par ID de projet et rôle
        Optional<ProjectMember> foundMember = projectMemberRepository.findByProjectIdAndRole(1L, "Observer");
        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getUserId()).isEqualTo(101L);
    }
}
