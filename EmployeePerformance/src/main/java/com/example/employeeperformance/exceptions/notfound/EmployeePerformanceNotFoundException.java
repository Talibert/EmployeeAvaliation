package com.example.employeeperformance.exceptions.notfound;

public class EmployeePerformanceNotFoundException extends RuntimeException{
    public EmployeePerformanceNotFoundException(String message){
        super(message);
    }

    public EmployeePerformanceNotFoundException(){
        super("Performance de funcionário não encontrada!");
    }
}
