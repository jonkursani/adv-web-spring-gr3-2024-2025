package dev.jonkursani.restapigr3.controllers;

import dev.jonkursani.restapigr3.dtos.auth.AuthResponse;
import dev.jonkursani.restapigr3.dtos.auth.LoginRequest;
import dev.jonkursani.restapigr3.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // hapi 1: Authenticate user
        var user = service.authenticate(request.getEmail(), request.getPassword());

        // hapi 2: Generate token
        var token = service.generateToken(user);

        var authResponse = new AuthResponse(token, 86400000L); // 24h in milliseconds

        return ResponseEntity.ok(authResponse);
    }
}