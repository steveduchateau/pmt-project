package com.example.pmt_backend.service;

import com.example.pmt_backend.model.Project;
import com.example.pmt_backend.model.ProjectMember;
import com.example.pmt_backend.Repository.ProjectRepository;
import com.example.pmt_backend.Repository.ProjectMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProjects() {
        // Arrange
        Project project1 = new Project();
        Project project2 = new Project();
        when(projectRepository.findAll()).thenReturn(Arrays.asList(project1, project2));

        // Act
        List<Project> result = projectService.getAllProjects();

        // Assert
        assertEquals(2, result.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void testGetProjectById() {
        // Arrange
        Long projectId = 1L;
        Project project = new Project();
        project.setId(projectId);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // Act
        Optional<Project> result = projectService.getProjectById(projectId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(projectId, result.get().getId());
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    public void testGetProjectById_NotFound() {
        // Arrange
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        // Act
        Optional<Project> result = projectService.getProjectById(projectId);

        // Assert
        assertFalse(result.isPresent());
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    public void testCreateProject() {
        // Arrange
        Project project = new Project();
        when(projectRepository.save(project)).thenReturn(project);

        // Act
        Project result = projectService.createProject(project);

        // Assert
        assertNotNull(result);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void testDeleteProject() {
        // Arrange
        Long projectId = 1L;

        // Act
        projectService.deleteProject(projectId);

        // Assert
        verify(projectRepository, times(1)).deleteById(projectId);
    }

    @Test
    public void testGetProjectCreator() {
        // Arrange
        Long projectId = 1L;
        Long creatorUserId = 100L;
        
        Project project = new Project();
        project.setId(projectId);
        project.setCreatorUserId(creatorUserId);
        
        ProjectMember creator = new ProjectMember();
        creator.setProjectId(projectId);
        creator.setUserId(creatorUserId);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectMemberRepository.findByProjectIdAndUserId(projectId, creatorUserId)).thenReturn(Optional.of(creator));

        // Act
        Optional<ProjectMember> result = projectService.getProjectCreator(projectId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(creatorUserId, result.get().getUserId());
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectMemberRepository, times(1)).findByProjectIdAndUserId(projectId, creatorUserId);
    }

    @Test
    public void testGetProjectCreator_ProjectNotFound() {
        // Arrange
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        // Act
        Optional<ProjectMember> result = projectService.getProjectCreator(projectId);

        // Assert
        assertFalse(result.isPresent());
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectMemberRepository, times(0)).findByProjectIdAndUserId(anyLong(), anyLong());
    }

    @Test
    public void testGetProjectCreator_CreatorNotFound() {
        // Arrange
        Long projectId = 1L;
        Long creatorUserId = 100L;
        
        Project project = new Project();
        project.setId(projectId);
        project.setCreatorUserId(creatorUserId);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectMemberRepository.findByProjectIdAndUserId(projectId, creatorUserId)).thenReturn(Optional.empty());

        // Act
        Optional<ProjectMember> result = projectService.getProjectCreator(projectId);

        // Assert
        assertFalse(result.isPresent());
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectMemberRepository, times(1)).findByProjectIdAndUserId(projectId, creatorUserId);
    }
}
