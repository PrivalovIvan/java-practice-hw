package ru.learn.javapracticehw.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JwtService {
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(HashMap<String, Object> extraClaims, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails user);
}
