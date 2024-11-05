package com.example.pmt_backend.service;

import com.example.pmt_backend.model.ProjectMember;
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

public class ProjectMemberServiceTest {

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @InjectMocks
    private ProjectMemberService projectMemberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMembersByProject() {
        // Arrange
        Long projectId = 1L;
        ProjectMember member1 = new ProjectMember();
        member1.setProjectId(projectId);
        ProjectMember member2 = new ProjectMember();
        member2.setProjectId(projectId);

        when(projectMemberRepository.findByProjectId(projectId)).thenReturn(Arrays.asList(member1, member2));

        // Act
        List<ProjectMember> result = projectMemberService.getMembersByProject(projectId);

        // Assert
        assertEquals(2, result.size());
        verify(projectMemberRepository, times(1)).findByProjectId(projectId);
    }

    @Test
    public void testAddMemberToProject() {
        // Arrange
        ProjectMember member = new ProjectMember();
        member.setProjectId(1L);
        when(projectMemberRepository.save(member)).thenReturn(member);

        // Act
        ProjectMember result = projectMemberService.addMemberToProject(member);

        // Assert
        assertNotNull(result);
        verify(projectMemberRepository, times(1)).save(member);
    }

    @Test
    public void testUpdateMemberRole() {
        // Arrange
        Long memberId = 1L;
        String newRole = "Manager";
        ProjectMember member = new ProjectMember();
        member.setId(memberId);
        member.setRole("Developer");

        when(projectMemberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(projectMemberRepository.save(member)).thenReturn(member);

        // Act
        ProjectMember result = projectMemberService.updateMemberRole(memberId, newRole);

        // Assert
        assertEquals(newRole, result.getRole());
        verify(projectMemberRepository, times(1)).findById(memberId);
        verify(projectMemberRepository, times(1)).save(member);
    }

    @Test
    public void testUpdateMemberRole_NotFound() {
        // Arrange
        Long memberId = 1L;
        String newRole = "Manager";

        when(projectMemberRepository.findById(memberId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> projectMemberService.updateMemberRole(memberId, newRole));
        assertEquals("Membre non trouvé", exception.getMessage());
        verify(projectMemberRepository, times(1)).findById(memberId);
    }

    @Test
    public void testRemoveMember() {
        // Arrange
        Long memberId = 1L;

        // Act
        projectMemberService.removeMember(memberId);

        // Assert
        verify(projectMemberRepository, times(1)).deleteById(memberId);
    }

    @Test
    public void testGetAllProjectMembers() {
        // Arrange
        ProjectMember member1 = new ProjectMember();
        ProjectMember member2 = new ProjectMember();
        when(projectMemberRepository.findAll()).thenReturn(Arrays.asList(member1, member2));

        // Act
        List<ProjectMember> result = projectMemberService.getAllProjectMembers();

        // Assert
        assertEquals(2, result.size());
        verify(projectMemberRepository, times(1)).findAll();
    }

    @Test
    public void testGetMemberByProjectAndUserId() {
        // Arrange
        Long projectId = 1L;
        Long userId = 100L;
        ProjectMember member = new ProjectMember();
        member.setProjectId(projectId);
        member.setUserId(userId);

        when(projectMemberRepository.findByProjectIdAndUserId(projectId, userId)).thenReturn(Optional.of(member));

        // Act
        Optional<ProjectMember> result = projectMemberService.getMemberByProjectAndUserId(projectId, userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getUserId());
        verify(projectMemberRepository, times(1)).findByProjectIdAndUserId(projectId, userId);
    }

    @Test
    public void testIsUserAdminInProject_UserIsAdmin() {
        // Arrange
        Long projectId = 1L;
        Long userId = 100L;
        ProjectMember member = new ProjectMember();
        member.setProjectId(projectId);
        member.setUserId(userId);
        member.setRole("admin");

        when(projectMemberRepository.findByProjectIdAndUserId(projectId, userId)).thenReturn(Optional.of(member));

        // Act
        boolean isAdmin = projectMemberService.isUserAdminInProject(projectId, userId);

        // Assert
        assertTrue(isAdmin);
        verify(projectMemberRepository, times(1)).findByProjectIdAndUserId(projectId, userId);
    }

    @Test
    public void testIsUserAdminInProject_UserIsNotAdmin() {
        // Arrange
        Long projectId = 1L;
        Long userId = 100L;
        ProjectMember member = new ProjectMember();
        member.setProjectId(projectId);
        member.setUserId(userId);
        member.setRole("user");

        when(projectMemberRepository.findByProjectIdAndUserId(projectId, userId)).thenReturn(Optional.of(member));

        // Act
        boolean isAdmin = projectMemberService.isUserAdminInProject(projectId, userId);

        // Assert
        assertFalse(isAdmin);
        verify(projectMemberRepository, times(1)).findByProjectIdAndUserId(projectId, userId);
    }

    @Test
    public void testIsUserAdminInProject_UserNotFound() {
        // Arrange
        Long projectId = 1L;
        Long userId = 100L;

        when(projectMemberRepository.findByProjectIdAndUserId(projectId, userId)).thenReturn(Optional.empty());

        // Act
        boolean isAdmin = projectMemberService.isUserAdminInProject(projectId, userId);

        // Assert
        assertFalse(isAdmin);
        verify(projectMemberRepository, times(1)).findByProjectIdAndUserId(projectId, userId);
    }
}
