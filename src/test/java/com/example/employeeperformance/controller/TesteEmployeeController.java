package com.example.employeeperformance.controller;

import com.example.employeeperformance.Fixtures.EmployeeFixture;
import com.example.employeeperformance.VOs.ChangeSetorVO;
import com.example.employeeperformance.VOs.EmployeeListResponseVO;
import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.exceptions.EmployeeSetorAlreadySettedException;
import com.example.employeeperformance.exceptions.invalid.InvalidAttributeException;
import com.example.employeeperformance.mappers.EmployeeVoMapper;
import com.example.employeeperformance.services.EmployeeService;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

class TesteEmployeeController {

    @Mock
    private EmployeeService employeeServiceMock;

    @Spy
    private EmployeeVoMapper employeeVoMapper;

    @Spy
    @InjectMocks
    private EmployeeController employeeControllerSpy;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeUpdateEmployee(){
        EmployeeVO employeeVO = new EmployeeVO();
        EmployeeVO employeeVOUpdated = new EmployeeVO();

        Mockito.when(employeeServiceMock.updateEmployee(employeeVO)).thenReturn(employeeVOUpdated);

        ResponseEntity<?> response = employeeControllerSpy.updateEmployee(employeeVO);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertTrue(response.hasBody());
    }

    @Test
    public void testeUpdateEmployeeInvalid(){
        EmployeeVO employeeVO = new EmployeeVO();

        Mockito.doCallRealMethod().when(employeeServiceMock).validatesEmployeeAttributes(employeeVO);

        Exception exception = Assertions.assertThrows(
                InvalidAttributeException.class, // Tipo da exceção esperada
                () -> employeeControllerSpy.updateEmployee(employeeVO)
        );

        Assertions.assertEquals("O atributo observação está inválido!", exception.getMessage());
    }

    @Test
    public void testeCreateEmployee(){
        EmployeeVO employeeVO = new EmployeeVO();
        EmployeeVO employeeVOCreated = new EmployeeVO();

        Mockito.when(employeeServiceMock.createEmployee(employeeVO)).thenReturn(employeeVOCreated);

        ResponseEntity<?> response = employeeControllerSpy.createEmployee(employeeVO);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertTrue(response.hasBody());
    }

    @Test
    public void testeCreateEmployeeInvalid(){
        EmployeeVO employeeVO = new EmployeeVO();

        Mockito.doCallRealMethod().when(employeeServiceMock).validatesEmployeeAttributes(employeeVO);

        Exception exception = Assertions.assertThrows(
                InvalidAttributeException.class, // Tipo da exceção esperada
                () -> employeeControllerSpy.createEmployee(employeeVO)
        );

        Assertions.assertEquals("O atributo observação está inválido!", exception.getMessage());
    }

    @Test
    public void testeChangeEmployeeSetorType(){
        ChangeSetorVO changeSetorVO = new ChangeSetorVO();

        ResponseEntity<?> response = employeeControllerSpy.changeEmployeeSetorType(1L, changeSetorVO);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(response.getBody(), "Setor do funcionário atualizado!");
    }

    @Test
    public void testeChangeEmployeeSetorTypeInvalid(){
        ChangeSetorVO changeSetorVO = new ChangeSetorVO();
        changeSetorVO.setSetorType(SetorType.OFFICE);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setSetorType(SetorType.OFFICE);

        Mockito.doCallRealMethod().when(employeeServiceMock).changeEmployeeSetorType(1L, changeSetorVO.getSetorType());
        Mockito.when(employeeServiceMock.findById(1L)).thenReturn(employee);

        Exception exception = Assertions.assertThrows(
                EmployeeSetorAlreadySettedException.class, // Tipo da exceção esperada
                () -> employeeControllerSpy.changeEmployeeSetorType(1L, changeSetorVO)
        );

        Assertions.assertEquals("O funcionário já possui a função informada!", exception.getMessage());
    }

    @Test
    public void testeChangeEmployeeSituationInativar(){
        Employee employee = new Employee();
        employee.setSituationType(SituationType.ATIVO);
        employee.setId(1L);
        employee.setSetorType(SetorType.OFFICE);

        Mockito.doCallRealMethod().when(employeeServiceMock).toogleEmployeeSituation(1L);
        Mockito.when(employeeServiceMock.findById(1L)).thenReturn(employee);

        ResponseEntity<?> response = employeeControllerSpy.changeEmployeeSituation(1L);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(response.getBody(), "Situação do funcionário atualizada!");
        Assertions.assertEquals(SituationType.INATIVO, employee.getSituationType());
    }

    @Test
    public void testeChangeEmployeeSituationAtivar(){
        Employee employee = new Employee();
        employee.setSituationType(SituationType.INATIVO);
        employee.setId(1L);
        employee.setSetorType(SetorType.OFFICE);

        Mockito.doCallRealMethod().when(employeeServiceMock).toogleEmployeeSituation(1L);
        Mockito.when(employeeServiceMock.findById(1L)).thenReturn(employee);

        ResponseEntity<?> response = employeeControllerSpy.changeEmployeeSituation(1L);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(response.getBody(), "Situação do funcionário atualizada!");
        Assertions.assertEquals(SituationType.ATIVO, employee.getSituationType());
    }

    @Test
    public void testeGetAllEmployeesWithFilters(){
        List<Employee> employeeList = EmployeeFixture.getEmployeeList();

        List<EmployeeVO> employeeVOList = employeeVoMapper.getListVO(employeeList);

        List<EmployeeVO> employeeReturn = employeeVOList.stream().filter(e -> e.getSituationType().equals(SituationType.ATIVO) && e.getSetorType().equals(SetorType.OFFICE)).toList();

        EmployeeListResponseVO employeeListResponseVO = new EmployeeListResponseVO(employeeReturn, "");

        Mockito.when(employeeServiceMock.findAllWithFilters(SetorType.OFFICE, SituationType.ATIVO)).thenReturn(employeeListResponseVO);

        ResponseEntity<?> response = employeeControllerSpy.getAllEmployeesWithFilters(SetorType.OFFICE, SituationType.ATIVO);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(response.getBody(), employeeReturn);
    }

    @Test
    public void testeGetAllEmployeesWithFiltersEmpty(){
        List<EmployeeVO> employeeReturn = new ArrayList<>();

        EmployeeListResponseVO employeeListResponseVO = new EmployeeListResponseVO(employeeReturn, "Não há funcionários cadastrados com essa combinação de Setor e Situação!");

        Mockito.when(employeeServiceMock.findAllWithFilters(SetorType.OFFICE, SituationType.ATIVO)).thenReturn(employeeListResponseVO);

        ResponseEntity<?> response = employeeControllerSpy.getAllEmployeesWithFilters(SetorType.OFFICE, SituationType.ATIVO);

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(response.getBody(), employeeListResponseVO.getErrorMessage());
    }
}