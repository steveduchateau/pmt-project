package com.example.pmt_backend.Repository;


import com.example.pmt_backend.model.Invitation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class InvitationRepositoryTest {

    @Autowired
    private InvitationRepository invitationRepository;

    @BeforeEach
    public void setUp() {
        // Clear the repository before each test to ensure isolation
        invitationRepository.deleteAll();
    }

    @Test
    public void testExistsByEmail_whenEmailExists_returnsTrue() {
        // Arrange
        Invitation invitation = new Invitation();
        invitation.setEmail("test@example.com");
        invitationRepository.save(invitation);

        // Act
        boolean exists = invitationRepository.existsByEmail("test@example.com");

        // Assert
        assertThat(exists).isTrue();
    }

    @Test
    public void testExistsByEmail_whenEmailDoesNotExist_returnsFalse() {
        // Act
        boolean exists = invitationRepository.existsByEmail("nonexistent@example.com");

        // Assert
        assertThat(exists).isFalse();
    }
}
