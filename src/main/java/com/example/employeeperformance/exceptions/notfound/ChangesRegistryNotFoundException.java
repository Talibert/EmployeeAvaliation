package com.example.employeeperformance.exceptions.notfound;

public class ChangesRegistryNotFoundException extends RuntimeException{
    public ChangesRegistryNotFoundException(String message){
        super(message);
    }

    public ChangesRegistryNotFoundException(){
        super("Registro de mudança de performance não encontrado!");
    }
}
