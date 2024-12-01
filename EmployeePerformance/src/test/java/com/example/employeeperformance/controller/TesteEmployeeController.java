package com.example.employeeperformance.controller;

import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.services.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

class TesteEmployeeController {

    @Mock
    private EmployeeService employeeServiceMock;

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

        ResponseEntity<?> response = employeeControllerSpy.updateEmployee(employeeVO);

        Assertions.assertEquals(HttpStatusCode.valueOf(400), response.getStatusCode());
        Assertions.assertTrue(response.hasBody());
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

        ResponseEntity<?> response = employeeControllerSpy.createEmployee(employeeVO);

        Assertions.assertEquals(HttpStatusCode.valueOf(400), response.getStatusCode());
        Assertions.assertTrue(response.hasBody());
    }
}