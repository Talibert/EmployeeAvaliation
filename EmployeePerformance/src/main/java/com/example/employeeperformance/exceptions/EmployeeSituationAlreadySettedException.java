package com.example.employeeperformance.exceptions;

public class EmployeeSituationAlreadySettedException extends RuntimeException{
    public EmployeeSituationAlreadySettedException(String message){
        super(message);
    }
}
