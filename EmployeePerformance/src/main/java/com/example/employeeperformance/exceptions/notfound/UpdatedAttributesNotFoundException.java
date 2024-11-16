package com.example.employeeperformance.exceptions.notfound;

public class UpdatedAttributesNotFoundException extends RuntimeException{
    public UpdatedAttributesNotFoundException(String message){
        super(message);
    }

    public UpdatedAttributesNotFoundException(){
        super("Atributos modificados não encontrados!");
    }
}
