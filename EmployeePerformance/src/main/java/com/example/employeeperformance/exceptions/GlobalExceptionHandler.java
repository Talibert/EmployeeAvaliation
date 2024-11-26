package com.example.employeeperformance.exceptions;

import com.example.employeeperformance.exceptions.invalid.InvalidAttributeException;
import com.example.employeeperformance.exceptions.notfound.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AttributesNotFoundException.class)
    public ResponseEntity<String> handleAttributeNotFoundException(AttributesNotFoundException attributesNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(attributesNotFoundException.getMessage());
    }

    @ExceptionHandler(ChangesRegistryNotFoundException.class)
    public ResponseEntity<String> handleChangesRegistryNotFoundException(ChangesRegistryNotFoundException changesRegistryNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(changesRegistryNotFoundException.getMessage());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employeeNotFoundException.getMessage());
    }

    @ExceptionHandler(EmployeePerformanceNotFoundException.class)
    public ResponseEntity<String> handleEmployeePerformanceNotFoundException(EmployeePerformanceNotFoundException employeePerformanceNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employeePerformanceNotFoundException.getMessage());
    }

    @ExceptionHandler(UpdatedAttributesNotFoundException.class)
    public ResponseEntity<String> handleUpdatedAttributeNotFoundException(UpdatedAttributesNotFoundException updatedAttributesNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedAttributesNotFoundException.getMessage());
    }

    @ExceptionHandler(InvalidAttributeException.class)
    public ResponseEntity<String> handleInvalidAttributeException(InvalidAttributeException invalidAttributeException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidAttributeException.getMessage());
    }

    @ExceptionHandler(EmployeeSituationAlreadySettedException.class)
    public ResponseEntity<String> handleEmployeeSituationAlreadySettedException(EmployeeSituationAlreadySettedException employeeSituationAlreadySettedException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employeeSituationAlreadySettedException.getMessage());
    }
}
