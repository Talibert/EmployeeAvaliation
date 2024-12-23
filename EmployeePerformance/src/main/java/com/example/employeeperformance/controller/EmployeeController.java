package com.example.employeeperformance.controller;

import com.example.employeeperformance.VOs.ChangeSetorVO;
import com.example.employeeperformance.VOs.EmployeeListResponseVO;
import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.exceptions.EmployeeSetorAlreadySettedException;
import com.example.employeeperformance.exceptions.invalid.InvalidAttributeException;
import com.example.employeeperformance.exceptions.notfound.EmployeeNotFoundException;
import com.example.employeeperformance.services.EmployeeService;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/{id}")
    public ResponseEntity<?> changeEmployeeSituation(@PathVariable Long id){
        try{
            employeeService.toogleEmployeeSituation(id);
            return ResponseEntity.status(HttpStatus.OK).body("Situação do funcionário atualizada!");
        } catch (EmployeeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Vamos retornar todos pois vamos deixar para o front-end controlar a exibição dos funcionários inativos
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllEmployeesWithFilters(
            @RequestParam(required = false) SetorType setorType,
            @RequestParam(required = false) SituationType situationType
    ){
        EmployeeListResponseVO employeeListResponseVO = employeeService.findAllWithFilters(setorType, situationType);

        if(!employeeListResponseVO.getMensagem().isBlank())
            return ResponseEntity.status(HttpStatus.OK).body(employeeListResponseVO.getMensagem());

        return ResponseEntity.status(HttpStatus.OK).body(employeeListResponseVO.getEmployeeVOList());
    }

    /**
     * Vamos retornar as informações de um funcionário no momento em que o usuário for editá-lo
     * @param id
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getEmployeeInfo(@PathVariable Long id){
        try{
            Employee employee = employeeService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        } catch (EmployeeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
