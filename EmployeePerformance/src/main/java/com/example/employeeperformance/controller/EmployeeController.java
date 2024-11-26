package com.example.employeeperformance.controller;

import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PutMapping
    public ResponseEntity<EmployeeVO> update(@RequestBody EmployeeVO employeeVO){
        employeeService.validatesEmployeeAttributes(employeeVO);

        EmployeeVO employeeVOUpdated =  employeeService.updateEmployee(employeeVO);

        return ResponseEntity.ok().body(employeeVOUpdated);
    }

    @PostMapping
    public ResponseEntity<EmployeeVO> create(@RequestBody EmployeeVO employeeVO){
        employeeService.validatesEmployeeAttributes(employeeVO);

        EmployeeVO employeeVOCreated = employeeService.createEmployee(employeeVO);

        return ResponseEntity.ok().body(employeeVOCreated);
    }
}
