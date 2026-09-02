package com.example.productapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="app_user", indexes=@Index(name="idx_user_username", columnList="username", unique=true))
@Getter @Setter @NoArgsConstructor
public class AppUser {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=100) private String username;
    @Column(nullable=false) private String password;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=20) private Role role;
    public AppUser(String username, String password, Role role) { this.username=username; this.password=password; this.role=role; }
}
