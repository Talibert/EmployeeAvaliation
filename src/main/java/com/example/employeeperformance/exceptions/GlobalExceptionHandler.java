package com.example.employeeperformance.exceptions;

import com.example.employeeperformance.VOs.ErrorResponseVO;
import com.example.employeeperformance.exceptions.invalid.InvalidAttributeException;
import com.example.employeeperformance.exceptions.notfound.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AttributesNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleAttributeNotFoundException(AttributesNotFoundException attributesNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseVO(attributesNotFoundException.getMessage(), "ATTRIBUTE_NOT_FOUND"));
    }

    @ExceptionHandler(ChangesRegistryNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleChangesRegistryNotFoundException(ChangesRegistryNotFoundException changesRegistryNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseVO(changesRegistryNotFoundException.getMessage(), "CHANGES_REGISTRY_NOT_FOUND"));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseVO(employeeNotFoundException.getMessage(), "EMPLOYEE_NOT_FOUND"));
    }

    @ExceptionHandler(EmployeePerformanceNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleEmployeePerformanceNotFoundException(EmployeePerformanceNotFoundException employeePerformanceNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseVO(employeePerformanceNotFoundException.getMessage(), "EMPLOYEE_PERFORMANCE_NOT_FOUND"));
    }

    @ExceptionHandler(UpdatedAttributesNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleUpdatedAttributeNotFoundException(UpdatedAttributesNotFoundException updatedAttributesNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseVO(updatedAttributesNotFoundException.getMessage(), "UPDATED_ATTRIBUTES_NOT_FOUND"));
    }

    @ExceptionHandler(InvalidAttributeException.class)
    public ResponseEntity<ErrorResponseVO> handleInvalidAttributeException(InvalidAttributeException invalidAttributeException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO(invalidAttributeException.getMessage(), "ATTRIBUTE_INVALID"));
    }

    @ExceptionHandler(EmployeeSetorAlreadySettedException.class)
    public ResponseEntity<ErrorResponseVO> handleEmployeeSituationAlreadySettedException(EmployeeSetorAlreadySettedException employeeSetorAlreadySettedException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO(employeeSetorAlreadySettedException.getMessage(), "SETOR_ALREADY_SETTED"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseVO> handleBadCredentialsException(BadCredentialsException badCredentialsException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO("Usuário ou senha inválidos!", "BAD_CREDENTIALS"));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO("Usuário não encontrado!", "USER_NOT_FOUND"));
    }
}
