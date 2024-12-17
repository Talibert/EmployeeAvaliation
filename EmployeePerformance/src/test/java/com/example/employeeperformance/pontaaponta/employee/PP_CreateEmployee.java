package com.example.employeeperformance.pontaaponta.employee;

import com.example.employeeperformance.EmployeeperformanceApplication;
import com.example.employeeperformance.Fixtures.AttributeFixture;
import com.example.employeeperformance.Fixtures.EmployeeFixture;
import com.example.employeeperformance.Fixtures.EmployeePerformanceFixture;
import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.business.UpdatePerformance;
import com.example.employeeperformance.controller.EmployeeController;
import com.example.employeeperformance.entities.*;
import com.example.employeeperformance.exceptions.notfound.EmployeeNotFoundException;
import com.example.employeeperformance.repositories.AbstractRepositoryTests;
import com.example.employeeperformance.repositories.AttributeRepository;
import com.example.employeeperformance.repositories.EmployeePerformanceRepository;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.services.ChangesRegistryService;
import com.example.employeeperformance.services.EmployeePerformanceService;
import com.example.employeeperformance.services.EmployeeService;
import com.example.employeeperformance.types.AttributeType;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest(classes = EmployeeperformanceApplication.class)
@ActiveProfiles("test")
public class PP_CreateEmployee extends AbstractRepositoryTests {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void init(){
        limpaBancos();
        resetID();
    }

    public void limpaBancos(){
        employeeRepository.deleteAll();
    }

    @Test
    public void testeCreateEmployee(){
        EmployeeVO employeeVOParaSalvar = new EmployeeVO();

        employeeVOParaSalvar.setCpf("50838402861");
        employeeVOParaSalvar.setNome("Guilherme Taliberti");
        employeeVOParaSalvar.setObservacao("Funcionário muito bom e dedicado que deve ser promovido!");
        employeeVOParaSalvar.setSetorType(SetorType.OFFICE);
        employeeVOParaSalvar.setSituationType(SituationType.ATIVO);

        ResponseEntity<?> response = employeeController.createEmployee(employeeVOParaSalvar);

        EmployeeVO employeeVOSaved = (EmployeeVO) response.getBody();

        Assertions.assertEquals(response.getStatusCode().value(), 200);
        Assertions.assertNotNull(employeeVOSaved);

        Optional<Employee> employeeSaved = employeeRepository.findById(employeeVOSaved.getId());

        if(employeeSaved.isPresent())
            fazAssertDoFuncionarioSalvo(employeeSaved.get(), employeeVOParaSalvar);
        else
            throw new EmployeeNotFoundException("O usuário salvo não foi encontrado no banco!");
    }

    private void fazAssertDoFuncionarioSalvo(Employee employeeSaved, EmployeeVO employeeVOParaSalvar){
        Assertions.assertEquals(employeeVOParaSalvar.getCpf(), employeeSaved.getCpf());
        Assertions.assertEquals(employeeVOParaSalvar.getNome(), employeeSaved.getNome());
        Assertions.assertEquals(employeeVOParaSalvar.getObservacao(), employeeSaved.getObservacao());
        Assertions.assertEquals(employeeVOParaSalvar.getSetorType(), employeeSaved.getSetorType());
        Assertions.assertEquals(employeeVOParaSalvar.getSituationType(), employeeSaved.getSituationType());
    }
}
