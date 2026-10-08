package ru.learn.javapracticehw.security.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.learn.javapracticehw.security.props.SecurityProps;
import ru.learn.javapracticehw.security.service.JwtService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final SecurityProps securityProps;

    @Override
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
            .claims(extraClaims)
            .signWith(getSignInKey(), Jwts.SIG.HS256)
            .subject(userDetails.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + securityProps.expiration()))
            .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails user) {
        var username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims, T> resolveClaims) {
        Claims claims = extractAllClaims(token);
        return resolveClaims.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(securityProps.secretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
