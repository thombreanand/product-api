package com.example.productapi.dto;
import jakarta.validation.constraints.NotBlank;
public final class AuthDtos {
    private AuthDtos() {}
    public record LoginRequest(@NotBlank String username,@NotBlank String password) {}
    public record RefreshRequest(@NotBlank String refreshToken) {}
    public record TokenResponse(String accessToken,String refreshToken,String tokenType,long expiresInSeconds) {}
    public record RegisterRequest(@NotBlank String username,@NotBlank String password) {}
}
