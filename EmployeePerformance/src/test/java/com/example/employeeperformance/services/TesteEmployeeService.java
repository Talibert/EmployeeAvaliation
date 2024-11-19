package com.example.employeeperformance.services;

import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.exceptions.notfound.EmployeeSituationAlreadySetted;
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
        } catch (EmployeeSituationAlreadySetted e) {
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
}
