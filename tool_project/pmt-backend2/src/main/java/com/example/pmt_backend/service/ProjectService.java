package com.example.pmt_backend.service;

import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.Repository.ProjectRepository;
import com.example.pmt_backend.Repository.ProjectMemberRepository;
import org.springframework.stereotype.Service;
import com.example.pmt_backend.model.ProjectMember;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    // @Autowired n'est pas nécessaire ici car c'est le seul constructeur
    public ProjectService(ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    // Ajout d'une méthode pour récupérer le créateur d'un projet
    public Optional<ProjectMember> getProjectCreator(Long projectId) {
        return projectRepository.findById(projectId)
                .flatMap(project -> projectMemberRepository.findByProjectIdAndUserId(project.getId(), project.getCreatorUserId()));
    }
}
