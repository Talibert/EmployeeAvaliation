package com.example.employeeperformance.types;

import java.util.List;

public enum UserRole {

    ADMIN(List.of("ROLE_ADMIN", "ROLE_USER")),
    MODERATOR(List.of("ROLE_MODERATOR", "ROLE_USER")),
    SUPPORT(List.of("ROLE_SUPPORT")),
    USER(List.of("ROLE_USER")),
    GUEST(List.of("ROLE_GUEST"));

    private final List<String> roles;

    UserRole(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }
}
