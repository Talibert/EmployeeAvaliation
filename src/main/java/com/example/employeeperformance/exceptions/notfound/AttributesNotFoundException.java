package com.example.employeeperformance.exceptions.notfound;

public class AttributesNotFoundException extends RuntimeException{
    public AttributesNotFoundException(String message){
        super(message);
    }

    public AttributesNotFoundException(){
        super("Atributo não encontrado!");
    }
}
