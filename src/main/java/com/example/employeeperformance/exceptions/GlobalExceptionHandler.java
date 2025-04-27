package com.example.employeeperformance.exceptions;

import com.example.employeeperformance.VOs.ErrorResponseVO;
import com.example.employeeperformance.exceptions.invalid.InvalidAttributeException;
import com.example.employeeperformance.exceptions.invalid.InvalidUserException;
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseVO(attributesNotFoundException.getMessage()));
    }

    @ExceptionHandler(ChangesRegistryNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleChangesRegistryNotFoundException(ChangesRegistryNotFoundException changesRegistryNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseVO(changesRegistryNotFoundException.getMessage()));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseVO(employeeNotFoundException.getMessage()));
    }

    @ExceptionHandler(EmployeePerformanceNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleEmployeePerformanceNotFoundException(EmployeePerformanceNotFoundException employeePerformanceNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseVO(employeePerformanceNotFoundException.getMessage()));
    }

    @ExceptionHandler(UpdatedAttributesNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleUpdatedAttributeNotFoundException(UpdatedAttributesNotFoundException updatedAttributesNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseVO(updatedAttributesNotFoundException.getMessage()));
    }

    @ExceptionHandler(InvalidAttributeException.class)
    public ResponseEntity<ErrorResponseVO> handleInvalidAttributeException(InvalidAttributeException invalidAttributeException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO(invalidAttributeException.getMessage()));
    }

    @ExceptionHandler(EmployeeSetorAlreadySettedException.class)
    public ResponseEntity<ErrorResponseVO> handleEmployeeSituationAlreadySettedException(EmployeeSetorAlreadySettedException employeeSetorAlreadySettedException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO(employeeSetorAlreadySettedException.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseVO> handleBadCredentialsException(BadCredentialsException badCredentialsException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO("Usuário ou senha inválidos!"));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO("Usuário não encontrado!"));
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorResponseVO> handleInvalidUserException(InvalidUserException invalidUserException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO(invalidUserException.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO(userNotFoundException.getMessage()));
    }
}
