package com.example.employeeperformance.exceptions;

public class EmployeeSetorAlreadySettedException extends RuntimeException{
    public EmployeeSetorAlreadySettedException(String message){
        super(message);
    }
}
