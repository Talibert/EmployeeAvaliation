package com.example.employeeperformance.VOs;

import com.example.employeeperformance.types.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO para encapsular um objeto de registro
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVO {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private UserRole userRole;

}
