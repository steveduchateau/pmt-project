package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.ProjectMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Rollback(false) // Empêche le rollback pour que les données soient persistées dans la base de données pour les tests
public class ProjectMemberRepositoryIntegrationTest {

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @BeforeEach
    public void setUp() {
        // Vous pouvez ajouter des données de test ici si nécessaire
        projectMemberRepository.deleteAll(); // Assurez-vous de partir d'un état propre
    }

    @Test
    public void testFindByProjectId() {
        // Préparer des données de test
        ProjectMember member1 = new ProjectMember();
        member1.setProjectId(1L);
        member1.setUserId(1L);
        member1.setRole("Admin");

        ProjectMember member2 = new ProjectMember();
        member2.setProjectId(1L);
        member2.setUserId(2L);
        member2.setRole("Observer");

        projectMemberRepository.save(member1);
        projectMemberRepository.save(member2);

        // Appeler la méthode et vérifier le résultat
        List<ProjectMember> members = projectMemberRepository.findByProjectId(1L);
        assertThat(members).hasSize(2);
        assertThat(members).extracting(ProjectMember::getUserId).containsExactlyInAnyOrder(1L, 2L);
    }

    @Test
    public void testFindByProjectIdAndUserId() {
        // Préparer des données de test
        ProjectMember member = new ProjectMember();
        member.setProjectId(1L);
        member.setUserId(1L);
        member.setRole("Admin");
        projectMemberRepository.save(member);

        // Appeler la méthode et vérifier le résultat
        Optional<ProjectMember> foundMember = projectMemberRepository.findByProjectIdAndUserId(1L, 1L);
        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getRole()).isEqualTo("Admin");
    }

    @Test
    public void testFindByProjectIdAndRole() {
        // Préparer des données de test
        ProjectMember member = new ProjectMember();
        member.setProjectId(1L);
        member.setUserId(1L);
        member.setRole("Admin");
        projectMemberRepository.save(member);

        // Appeler la méthode et vérifier le résultat
        Optional<ProjectMember> foundMember = projectMemberRepository.findByProjectIdAndRole(1L, "Admin");
        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getUserId()).isEqualTo(1L);
    }
}

