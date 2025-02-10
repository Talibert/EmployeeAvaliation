package com.example.employeeperformance.pontaaponta.employee;

import com.example.employeeperformance.EmployeeperformanceApplication;
import com.example.employeeperformance.Fixtures.EmployeeFixture;
import com.example.employeeperformance.VOs.ChangeSetorVO;
import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.controller.EmployeeController;
import com.example.employeeperformance.entities.*;
import com.example.employeeperformance.repositories.AbstractRepositoryTests;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.services.EmployeeService;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

/**
 * Classe de testes ponta a ponta que envolvem os processos de funcionários
 */
@SpringBootTest(classes = EmployeeperformanceApplication.class)
@ActiveProfiles("test")
public class PP_EmployeeProcess extends AbstractRepositoryTests {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void init(){
        limpaBancos();
        resetID();
        populaBanco();
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

        Employee employeeSaved = employeeService.findById(employeeVOSaved.getId());

        fazAssertDoFuncionarioSalvo(employeeSaved, employeeVOParaSalvar);
    }

    private void fazAssertDoFuncionarioSalvo(Employee employeeSaved, EmployeeVO employeeVOParaSalvar){
        Assertions.assertEquals(employeeVOParaSalvar.getCpf(), employeeSaved.getCpf());
        Assertions.assertEquals(employeeVOParaSalvar.getNome(), employeeSaved.getNome());
        Assertions.assertEquals(employeeVOParaSalvar.getObservacao(), employeeSaved.getObservacao());
        Assertions.assertEquals(employeeVOParaSalvar.getSetorType(), employeeSaved.getSetorType());
        Assertions.assertEquals(employeeVOParaSalvar.getSituationType(), employeeSaved.getSituationType());
    }

    @Test
    public void testeUpdateEmployee(){
        Employee employeeAntigo = employeeService.findById(1L);
        EmployeeVO employeeVO = new EmployeeVO();

        employeeVO.setId(1L);
        employeeVO.setNome("Yone");
        employeeVO.setCpf("999.999.999-99");
        employeeVO.setObservacao("Funcionário muito bom e dedicado que deve ser promovido!");
        employeeVO.setSetorType(SetorType.STOCK);
        employeeVO.setSituationType(SituationType.ATIVO);

        ResponseEntity<?> response = employeeController.updateEmployee(employeeVO);

        EmployeeVO employeeVOSaved = (EmployeeVO) response.getBody();

        Assertions.assertEquals(response.getStatusCode().value(), 200);
        Assertions.assertNotNull(employeeVOSaved);

        Employee newEmployee = employeeService.findById(1L);

        fazAssertDoFuncionarioAtualizado(newEmployee, employeeAntigo, employeeVO);
    }

    private void fazAssertDoFuncionarioAtualizado(Employee newEmployee, Employee employeeAntigo, EmployeeVO employeeVO){
        Assertions.assertEquals(employeeVO.getCpf(), newEmployee.getCpf());
        Assertions.assertEquals(employeeVO.getNome(), newEmployee.getNome());
        Assertions.assertEquals(employeeVO.getObservacao(), newEmployee.getObservacao());
        Assertions.assertEquals(employeeVO.getSetorType(), newEmployee.getSetorType());
        Assertions.assertEquals(employeeVO.getSituationType(), newEmployee.getSituationType());

        Assertions.assertNotEquals(employeeAntigo.getCpf(), newEmployee.getCpf());
        Assertions.assertNotEquals(employeeAntigo.getNome(), newEmployee.getNome());
        Assertions.assertNotEquals(employeeAntigo.getObservacao(), newEmployee.getObservacao());
        Assertions.assertNotEquals(employeeAntigo.getSetorType(), newEmployee.getSetorType());
    }

    @Test
    public void testeChangeEmployeeSetorType(){
        Employee employeeAntigo = employeeService.findById(1L);

        // Confirmando que o usuário recuperado era do setor OFFICE
        Assertions.assertEquals( SetorType.OFFICE, employeeAntigo.getSetorType());

        ChangeSetorVO changeSetorVO = new ChangeSetorVO();

        changeSetorVO.setSetorType(SetorType.STOCK);

        ResponseEntity<?> response = employeeController.changeEmployeeSetorType(1L, changeSetorVO);

        Assertions.assertEquals(response.getStatusCode().value(), 200);
        Assertions.assertEquals(response.getBody(), "Setor do funcionário atualizado!");

        Employee employeeAtualizado = employeeService.findById(1L);

        Assertions.assertEquals(SetorType.STOCK, employeeAtualizado.getSetorType());
    }

    @Test
    public void testeChangeEmployeeSituation(){
        Employee employeeAntigo = employeeService.findById(1L);

        // Confirmando que o usuário recuperado era do setor OFFICE
        Assertions.assertEquals(SituationType.ATIVO, employeeAntigo.getSituationType());

        ResponseEntity<?> response = employeeController.changeEmployeeSituation(1L);

        Assertions.assertEquals(response.getStatusCode().value(), 200);
        Assertions.assertEquals(response.getBody(), "Situação do funcionário atualizada!");

        Employee employeeAtualizado = employeeService.findById(1L);

        Assertions.assertEquals(SituationType.INATIVO, employeeAtualizado.getSituationType());
    }

    private void populaBanco(){
        List<Employee> employeeList = EmployeeFixture.getEmployeeList();

        employeeRepository.saveAll(employeeList);
    }
}
