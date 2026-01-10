package com.aespa.armageddon.core.domain.auth.repository;

import com.aespa.armageddon.core.domain.auth.entity.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {
    Optional<EmailVerificationToken> findTopByEmailOrderByCreatedAtDesc(String email);

    void deleteByEmail(String email);
}
