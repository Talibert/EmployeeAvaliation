package com.example.employeeperformance.business;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.mappers.EmployeePerformanceVoMapper;
import com.example.employeeperformance.services.EmployeePerformanceService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class TesteUpdatePerformance {

    @Mock
    private EmployeePerformanceService employeePerformanceService;

    @Mock
    private EmployeePerformanceVoMapper employeePerformanceVoMapper;

    @Spy
    @InjectMocks
    private UpdatePerformance updatePerformance;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePerformanceUpdate() {
        LocalDate data = LocalDate.of(2024, Month.JANUARY, 1);
        Employee employee = new Employee();
        employee.setId(1L);
        EmployeePerformanceVO employeePerformanceVO = new EmployeePerformanceVO(1L, data, 4.0, 3.0, 5.0, 4.0, 3.0);

        EmployeePerformance lastEmployeePerformance = new EmployeePerformance(data, employee);
        lastEmployeePerformance.setId(1L);

        EmployeePerformance savedEmployeePerformance = new EmployeePerformance(data, employee);
        lastEmployeePerformance.setId(2L);

        Mockito.when(employeePerformanceVoMapper.getEntity(employeePerformanceVO)).thenCallRealMethod();
        Mockito.doReturn(lastEmployeePerformance).when(employeePerformanceService).findByMesAnoEEmployeeLastRegistry(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doReturn(lastEmployeePerformance).when(employeePerformanceService).saveEmployeePerformance(Mockito.any());
        Mockito.doNothing().when(updatePerformance).saveAttributes(savedEmployeePerformance, employeePerformanceVO);
        Mockito.doNothing().when(updatePerformance).saveChangesRegistry(savedEmployeePerformance, lastEmployeePerformance);

        updatePerformance.savePerformanceUpdate(employeePerformanceVO);

        Mockito.verify(updatePerformance).saveAttributes(savedEmployeePerformance, employeePerformanceVO);
        Mockito.verify(updatePerformance).saveChangesRegistry(savedEmployeePerformance, lastEmployeePerformance);
    }

    @Test
    void saveAttributes() {
    }

    @Test
    void saveChangesRegistry() {
    }

    @Test
    void saveUpdatedAttributes() {
    }
}