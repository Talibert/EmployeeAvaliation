package com.example.employeeperformance.VOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseVO {

    private String error;
    private String code;
}
