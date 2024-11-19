package com.example.employeeperformance.exceptions.notfound;

public class InvalidAttributeException extends RuntimeException{
    public InvalidAttributeException(){
        super("Atributos inválidos!");
    }

    public InvalidAttributeException(String message){
        super(message);
    }
}
