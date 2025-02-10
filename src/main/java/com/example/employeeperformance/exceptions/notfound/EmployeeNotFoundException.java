package com.example.employeeperformance.exceptions.notfound;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message){
        super(message);
    }

    public EmployeeNotFoundException(){
        super("Funcionário não encontrado!");
    }
}
