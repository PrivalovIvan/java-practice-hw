package ru.learn.javapracticehw.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learn.javapracticehw.security.dto.JwtAuthenticatedResponse;
import ru.learn.javapracticehw.security.dto.LoginRequest;
import ru.learn.javapracticehw.security.service.JwtService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @PostMapping("/auth/login")
    ResponseEntity<JwtAuthenticatedResponse> login(@RequestBody LoginRequest loginRequest) {
        var login = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        var auth = authManager.authenticate(login);
        var token = jwtService.generateToken((UserDetails) auth.getPrincipal());

        return ResponseEntity.ok(new JwtAuthenticatedResponse(token));
    }
}
