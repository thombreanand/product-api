package com.example.productapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name="refresh_token", indexes={@Index(name="idx_refresh_token_hash", columnList="token_hash", unique=true), @Index(name="idx_refresh_user", columnList="user_id")})
@Getter @Setter @NoArgsConstructor
public class RefreshToken {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="token_hash", nullable=false, unique=true, length=64) private String tokenHash;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="user_id", nullable=false) private AppUser user;
    @Column(nullable=false) private Instant expiresAt;
    @Column(nullable=false) private boolean revoked;
    @Column(nullable=false, length=64) private String familyId;
}
