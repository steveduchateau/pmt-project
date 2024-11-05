package com.example.pmt_backend.Repository;


import com.example.pmt_backend.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Rollback(false) // Permet de garder les données dans la base de données après le test
public class ProjectRepositoryIntegrationTest {

    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setUp() {
        projectRepository.deleteAll(); // Commence chaque test avec une base de données propre
    }

    @Test
    public void testSaveAndFindProject() {
        // Préparer un projet pour le test
        Project project = new Project();
        project.setName("Test Project");
        project.setDescription("Description of Test Project");

        // Sauvegarder le projet
        projectRepository.save(project);

        // Vérifier que le projet est bien enregistré
        Optional<Project> foundProject = projectRepository.findById(project.getId());
        assertThat(foundProject).isPresent();
        assertThat(foundProject.get().getName()).isEqualTo("Test Project");
        assertThat(foundProject.get().getDescription()).isEqualTo("Description of Test Project");
    }

    @Test
    public void testDeleteProject() {
        // Préparer un projet pour le test
        Project project = new Project();
        project.setName("Project to Delete");
        project.setDescription("This project will be deleted.");

        // Sauvegarder le projet
        projectRepository.save(project);

        // Vérifier que le projet existe avant la suppression
        Optional<Project> foundProjectBeforeDelete = projectRepository.findById(project.getId());
        assertThat(foundProjectBeforeDelete).isPresent();

        // Supprimer le projet
        projectRepository.delete(project);

        // Vérifier que le projet n'existe plus après la suppression
        Optional<Project> foundProjectAfterDelete = projectRepository.findById(project.getId());
        assertThat(foundProjectAfterDelete).isNotPresent();
    }
}
