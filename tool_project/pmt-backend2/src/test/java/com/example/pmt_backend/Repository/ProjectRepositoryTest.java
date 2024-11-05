package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    private Project project;

    @BeforeEach
    public void setUp() {
        // Création d'une instance de test pour Project
        project = new Project();
        project.setName("Project A");
        project.setDescription("Description for Project A");
        
        // Enregistrement de l'instance dans la base de données de test
        project = projectRepository.save(project);
    }

    @Test
    public void testSaveProject() {
        // Vérifier que le projet a été sauvegardé avec un ID généré
        assertThat(project.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        // Vérifier la récupération du projet par ID
        Optional<Project> foundProject = projectRepository.findById(project.getId());
        assertThat(foundProject).isPresent();
        assertThat(foundProject.get().getName()).isEqualTo("Project A");
    }

    @Test
    public void testUpdateProject() {
        // Modification de l'entité Project et sauvegarde
        project.setName("Updated Project A");
        Project updatedProject = projectRepository.save(project);

        // Vérifier que les changements sont sauvegardés
        assertThat(updatedProject.getName()).isEqualTo("Updated Project A");
    }

    @Test
    public void testDeleteProject() {
        // Suppression du projet de la base de données
        projectRepository.delete(project);

        // Vérifier que le projet n'est plus présent
        Optional<Project> deletedProject = projectRepository.findById(project.getId());
        assertThat(deletedProject).isNotPresent();
    }
}
