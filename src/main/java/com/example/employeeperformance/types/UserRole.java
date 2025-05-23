package com.example.employeeperformance.types;

import java.util.List;

/**
 * Esse enum vai definir as roles que cada usuário pode ter acesso.
 * Cada enum devolve uma lista com os tipos de acesso permitidos
 *
 * Essa lista é resgatada no getAuthorities da classe User e o Spring-Security usa elas para validar no filter-chain e pre-authorize
 */
public enum UserRole {

    // TODO essas roles ainda não definitivas, é apenas um esboço
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
