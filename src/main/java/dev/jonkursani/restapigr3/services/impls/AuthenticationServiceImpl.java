package dev.jonkursani.restapigr3.services.impls;

import dev.jonkursani.restapigr3.services.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;
    private final Long expireTimeInMs = 86400000L; // 24h in milliseconds

    @Override
    public UserDetails authenticate(String email, String password) {
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // Load the user details
        return userDetailsService.loadUserByUsername(email);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeInMs))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @Override
    public UserDetails validateToken(String token) {
        String email = extractUsername(token);
        return userDetailsService.loadUserByUsername(email);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }












}