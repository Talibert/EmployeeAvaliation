package com.example.employeeperformance.services;

import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.exceptions.EmployeeSetorAlreadySettedException;
import com.example.employeeperformance.exceptions.invalid.InvalidAttributeException;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class TesteEmployeeService {

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @Spy
    @InjectMocks
    private EmployeeService employeeServiceSpy;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Nesse cenário o funcionário retornado possui o mesmo setor que o informado para alteração
     */
    @Test
    public void testeChangeEmployeeSetorTypeCenario1(){
        Employee employee = new Employee("Yasuo", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.ATIVO);

        Mockito.doReturn(employee).when(employeeServiceSpy).findById(1L);

        try{
            employeeServiceSpy.changeEmployeeSetorType(1L, SetorType.OFFICE);
        } catch (EmployeeSetorAlreadySettedException e) {
            Assertions.assertEquals("O funcionário já possui a função informada", e.getMessage());
        }
    }

    /**
     * Nesse cenário o funcionário retornado não possui o mesmo setor informado
     */
    @Test
    public void testeChangeEmployeeSetorType(){
        Employee employee = new Employee("Yasuo", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.ATIVO);

        Mockito.doReturn(employee).when(employeeServiceSpy).findById(1L);

        employeeServiceSpy.changeEmployeeSetorType(1L, SetorType.STOCK);

        Assertions.assertEquals(employee.getSetorType(), SetorType.STOCK);
        Mockito.verify(employeeRepositoryMock).save(employee);
    }

    /**
     * Nesse cenário o funcionário está ativo, portanto ele deve ser inativado
     */
    @Test
    public void testeToggleEmployeeSituationCenario1(){
        Employee employee = new Employee("Yasuo", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.ATIVO);

        Mockito.doReturn(employee).when(employeeServiceSpy).findById(1L);

        employeeServiceSpy.toogleEmployeeSituation(1L);

        Assertions.assertEquals(employee.getSituationType(), SituationType.INATIVO);
        Mockito.verify(employeeRepositoryMock).save(employee);
    }

    /**
     * Nesse cenário o funcionário está inativo, portanto ele deve ser ativado
     */
    @Test
    public void testeToggleEmployeeSituationCenario2(){
        Employee employee = new Employee("Yasuo", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.INATIVO);

        Mockito.doReturn(employee).when(employeeServiceSpy).findById(1L);

        employeeServiceSpy.toogleEmployeeSituation(1L);

        Assertions.assertEquals(employee.getSituationType(), SituationType.ATIVO);
        Mockito.verify(employeeRepositoryMock).save(employee);
    }

    /**
     * Nesse cenário não haverá lançamento de exceções
     */
    @Test
    public void testeValidatesEmployeeAttributesCenario1(){
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setCpf("Cpf teste");
        employeeVO.setNome("Nome teste");
        employeeVO.setObservacao("Observação teste");

        employeeServiceSpy.validatesEmployeeAttributes(employeeVO);
    }

    @Test
    public void testeValidatesEmployeeAttributeCenario2(){
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setNome("Nome teste");
        employeeVO.setObservacao("Observação teste");

        try{
            employeeServiceSpy.validatesEmployeeAttributes(employeeVO);
        } catch (InvalidAttributeException e){
            Assertions.assertEquals("O atributo cpf está inválido!", e.getMessage());
        }
    }

    @Test
    public void testeValidatesEmployeeAttributeCenario3(){
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setCpf("Cpf teste");
        employeeVO.setObservacao("Observação teste");

        try{
            employeeServiceSpy.validatesEmployeeAttributes(employeeVO);
        } catch (InvalidAttributeException e){
            Assertions.assertEquals("O atributo nome está inválido!", e.getMessage());
        }
    }

    @Test
    public void testeValidatesEmployeeAttributeCenario4(){
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setCpf("Cpf teste");
        employeeVO.setNome("Nome teste");

        try{
            employeeServiceSpy.validatesEmployeeAttributes(employeeVO);
        } catch (InvalidAttributeException e){
            Assertions.assertEquals("O atributo observação está inválido!", e.getMessage());
        }
    }
}
