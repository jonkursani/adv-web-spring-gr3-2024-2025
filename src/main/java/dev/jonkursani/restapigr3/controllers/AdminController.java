package dev.jonkursani.restapigr3.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')") // te kontrolleri e keni hasRole
public class AdminController {
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')") // te metoda ose te endponinti e keni hasAuthority
    public String get() {
        return "GET: admin controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public String post() {
        return "POST: admin controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public String put() {
        return "PUT: admin controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete() {
        return "DELETE: admin controller";
    }
}