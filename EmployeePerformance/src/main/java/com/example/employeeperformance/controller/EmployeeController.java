package com.example.employeeperformance.controller;

import com.example.employeeperformance.VOs.ChangeSetorVO;
import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.exceptions.EmployeeSetorAlreadySettedException;
import com.example.employeeperformance.exceptions.invalid.InvalidAttributeException;
import com.example.employeeperformance.exceptions.notfound.EmployeeNotFoundException;
import com.example.employeeperformance.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeVO employeeVO){
        try{
            employeeService.validatesEmployeeAttributes(employeeVO);
            EmployeeVO employeeVOUpdated =  employeeService.updateEmployee(employeeVO);
            return ResponseEntity.status(HttpStatus.OK).body(employeeVOUpdated);
        } catch (InvalidAttributeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EmployeeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeVO employeeVO){
        try{
            employeeService.validatesEmployeeAttributes(employeeVO);
            EmployeeVO employeeVOCreated = employeeService.createEmployee(employeeVO);
            return ResponseEntity.status(HttpStatus.OK).body(employeeVOCreated);
        } catch (InvalidAttributeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/setor")
    public ResponseEntity<?> changeEmployeeSetorType(@PathVariable Long id, @RequestBody ChangeSetorVO changeSetorVO){
        try{
            employeeService.changeEmployeeSetorType(id, changeSetorVO.getSetorType());
            return ResponseEntity.status(HttpStatus.OK).body("Setor do funcionário atualizado!");
        } catch (EmployeeSetorAlreadySettedException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
