package ru.learn.javapracticehw.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.learn.javapracticehw.common.enums.Role;
import ru.learn.javapracticehw.security.model.SecurityUser;
import ru.learn.javapracticehw.security.service.JwtService;
import ru.learn.javapracticehw.user.entity.User;
import ru.learn.javapracticehw.user.repository.UserRepository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication auth
    ) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) auth.getPrincipal();
        log.info("OAuth2User: {}", oAuth2User);
        assert oAuth2User != null;
        String email = oAuth2User.getAttribute("email");

        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoder.encode("OAUTH2_GENERATED_PASSWORD"));
            newUser.setRole(Role.AUDITOR);

            return userRepository.save(newUser);
        });

        String jwtToken = jwtService.generateToken(new SecurityUser(user));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", jwtToken);
        response.getWriter().write(objectMapper.writeValueAsString(tokenMap));
    }
}
