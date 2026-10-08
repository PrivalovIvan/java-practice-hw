package ru.learn.javapracticehw.security.dto;

public record JwtAuthenticatedResponse(
    String accessToken,
    String refreshToken
) {
    public JwtAuthenticatedResponse(String accessToken) {
        this(accessToken, "");
    }
}
