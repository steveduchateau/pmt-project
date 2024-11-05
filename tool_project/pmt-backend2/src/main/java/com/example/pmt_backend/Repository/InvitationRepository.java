package com.example.pmt_backend.Repository;

import com.example.pmt_backend.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    boolean existsByEmail(String email);
}
