package com.example.employeeperformance.controller;

import com.example.employeeperformance.VOs.ChangeSetorVO;
import com.example.employeeperformance.VOs.EmployeeListResponseVO;
import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.services.EmployeeService;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
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
    public ResponseEntity<EmployeeVO> updateEmployee(@RequestBody EmployeeVO employeeVO){
        employeeService.validatesEmployeeAttributes(employeeVO);
        EmployeeVO employeeVOUpdated = employeeService.updateEmployee(employeeVO);

        return ResponseEntity.ok().body(employeeVOUpdated);
    }

    @PostMapping
    public ResponseEntity<EmployeeVO> createEmployee(@RequestBody EmployeeVO employeeVO){
        employeeService.validatesEmployeeAttributes(employeeVO);
        EmployeeVO employeeVOCreated = employeeService.createEmployee(employeeVO);

        return ResponseEntity.ok().body(employeeVOCreated);
    }

    @PatchMapping("/{id}/setor")
    public ResponseEntity<String> changeEmployeeSetorType(@PathVariable Long id, @RequestBody ChangeSetorVO changeSetorVO){
        employeeService.changeEmployeeSetorType(id, changeSetorVO.getSetorType());

        return ResponseEntity.ok().body("Setor do funcionário atualizado!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> changeEmployeeSituation(@PathVariable Long id){
        employeeService.toogleEmployeeSituation(id);

        return ResponseEntity.ok().body("Situação do funcionário atualizada!");
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

        if(!employeeListResponseVO.getErrorMessage().isBlank())
            return ResponseEntity.status(HttpStatus.OK).body(employeeListResponseVO.getErrorMessage());

        return ResponseEntity.status(HttpStatus.OK).body(employeeListResponseVO.getEmployeeVOList());
    }

//    /**
//     * Vamos retornar as informações de um funcionário no momento em que o usuário for editá-lo
//     * @param id
//     * @return
//     */
//    @GetMapping
//    public ResponseEntity<?> getEmployeeInfo(@PathVariable Long id){
//        try{
//            Employee employee = employeeService.findById(id);
//            return ResponseEntity.status(HttpStatus.OK).body(employee);
//        } catch (EmployeeNotFoundException e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
}
