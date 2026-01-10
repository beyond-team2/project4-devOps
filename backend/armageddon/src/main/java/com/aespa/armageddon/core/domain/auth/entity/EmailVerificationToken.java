package com.aespa.armageddon.core.domain.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "email_verification_token",
        indexes = {
                @Index(name = "idx_evt_email_created", columnList = "email, createdAt")
        })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailVerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 64)
    private String codeHash;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime verifiedAt;

    private LocalDateTime usedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public static EmailVerificationToken create(String email, String codeHash, Duration ttl) {
        EmailVerificationToken token = new EmailVerificationToken();
        token.email = email;
        token.codeHash = codeHash;
        token.createdAt = LocalDateTime.now();
        token.expiresAt = token.createdAt.plus(ttl);
        return token;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean isVerified() {
        return verifiedAt != null;
    }

    public boolean isUsed() {
        return usedAt != null;
    }

    public void markVerified() {
        this.verifiedAt = LocalDateTime.now();
    }

    public void markUsed() {
        this.usedAt = LocalDateTime.now();
    }
}
