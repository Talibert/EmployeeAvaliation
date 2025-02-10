package com.example.employeeperformance.exceptions.invalid;

public class InvalidAttributeException extends RuntimeException{
    public InvalidAttributeException(){
        super("Atributos inválidos!");
    }

    public InvalidAttributeException(String message){
        super(message);
    }
}
