package dev.jonkursani.restapigr3.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    MANAGER_READ("manager:read"),
    MANAGER_WRITE("manager:write");

    @Getter
    private final String permission;
}