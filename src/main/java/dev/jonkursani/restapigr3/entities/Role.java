package dev.jonkursani.restapigr3.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static dev.jonkursani.restapigr3.entities.Permission.*;

@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(ADMIN_READ, ADMIN_WRITE, MANAGER_READ, MANAGER_WRITE)),
    MANAGER(Set.of(MANAGER_WRITE, MANAGER_READ)),
    EMPLOYEE(Set.of(EMPLOYEE_WRITE, EMPLOYEE_READ)),
    USER(Collections.emptySet());

    @Getter
    // Liste me permission
    private final Set<Permission> permissions;

    // per mi tregu spring security cilat permission (authority) i ka ky rol
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authority = new java.util.ArrayList<>(
                getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList());

        // Konventa e spring security duhet qe rolet te fillojne me ROLE_
        authority.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authority;
    }
}