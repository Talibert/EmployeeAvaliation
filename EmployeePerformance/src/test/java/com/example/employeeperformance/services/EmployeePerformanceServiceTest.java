package com.example.employeeperformance.services;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.calculations.PerformanceMetric;
import com.example.employeeperformance.entities.Attribute;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.types.AttributeType;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeePerformanceServiceTest {

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @Mock
    private EmployeeService employeeServiceMock;

    @Spy
    @InjectMocks
    private EmployeePerformanceService employeePerformanceServiceSpy;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeGetEmployeePerformanceAverageByYearAndMonth(){
        Month month = Month.of(1);
        Year year = Year.of(2024);
        Long id = 1L;

        Employee employee = new Employee("Yasuo", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.INATIVO);
        List<EmployeePerformance> employeePerformanceList = new ArrayList<>();
        Map<AttributeType, PerformanceMetric> performanceMap = new HashMap<>();
        EmployeePerformanceVO vo = new EmployeePerformanceVO();

        Mockito.when(employeeServiceMock.findById(id)).thenReturn(employee);
        Mockito.doReturn(employeePerformanceList).when(employeePerformanceServiceSpy).findByYearMonthAndEmployee(month, year, employee);
        Mockito.doReturn(performanceMap).when(employeePerformanceServiceSpy).popularPerformanceMap(employeePerformanceList);
        Mockito.doReturn(vo).when(employeePerformanceServiceSpy).getEmployeePerformanceAverage(performanceMap, id, month, year);

        employeePerformanceServiceSpy.getEmployeePerformanceAverageByYearAndMonth(month, year, id);

        Mockito.verify(employeePerformanceServiceSpy).findByYearMonthAndEmployee(month, year, employee);
        Mockito.verify(employeePerformanceServiceSpy).popularPerformanceMap(employeePerformanceList);
        Mockito.verify(employeePerformanceServiceSpy).getEmployeePerformanceAverage(performanceMap, id, month, year);
    }

    @Test
    public void testePopularPerformanceMap(){
        EmployeePerformance employeePerformance1 = new EmployeePerformance();
        EmployeePerformance employeePerformance2 = new EmployeePerformance();
        EmployeePerformance employeePerformance3 = new EmployeePerformance();
        EmployeePerformance employeePerformance4 = new EmployeePerformance();
        EmployeePerformance employeePerformance5 = new EmployeePerformance();
        EmployeePerformance employeePerformance6 = new EmployeePerformance();
        EmployeePerformance employeePerformance7 = new EmployeePerformance();
        EmployeePerformance employeePerformance8 = new EmployeePerformance();
        EmployeePerformance employeePerformance9 = new EmployeePerformance();
        EmployeePerformance employeePerformance10 = new EmployeePerformance();

        List<EmployeePerformance> employeePerformanceList = List.of(employeePerformance1, employeePerformance2, employeePerformance3,
                employeePerformance4, employeePerformance5, employeePerformance6, employeePerformance7, employeePerformance8, employeePerformance9,
                employeePerformance10);

        criaListas(employeePerformanceList);

        Map<AttributeType, PerformanceMetric> map = employeePerformanceServiceSpy.popularPerformanceMap(employeePerformanceList);

        Assertions.assertEquals(30, map.get(AttributeType.PONCTUALITY).getValue());
        Assertions.assertEquals(40, map.get(AttributeType.WORK_DELIVERY).getValue());
        Assertions.assertEquals(50, map.get(AttributeType.PPE_USAGE).getValue());
        Assertions.assertEquals(40, map.get(AttributeType.EVOLUTION).getValue());
        Assertions.assertEquals(40, map.get(AttributeType.COMMITMENT).getValue());
    }

    @Test
    public void testeGetEmployeePerformanceAverage(){
        Month month = Month.of(1);
        Year year = Year.of(2024);
        Long id = 1L;
        Map<AttributeType, PerformanceMetric> performanceMap = new HashMap<>();

        Double ponctuality = 41.0;
        Double workDelivery = 40.0;
        Double ppeUsage = 44.0;
        Double evolution = 40.0;
        Double commitment = 44.0;

        performanceMap.put(AttributeType.PONCTUALITY, new PerformanceMetric(10, ponctuality));
        performanceMap.put(AttributeType.WORK_DELIVERY, new PerformanceMetric(10, workDelivery));
        performanceMap.put(AttributeType.PPE_USAGE, new PerformanceMetric(10, ppeUsage));
        performanceMap.put(AttributeType.EVOLUTION, new PerformanceMetric(10, evolution));
        performanceMap.put(AttributeType.COMMITMENT, new PerformanceMetric(10, commitment));

        EmployeePerformanceVO employeePerformanceVOResult = employeePerformanceServiceSpy.getEmployeePerformanceAverage(performanceMap, id, month, year);

        Assertions.assertEquals(4.1, employeePerformanceVOResult.getPonctuality());
        Assertions.assertEquals(4.0, employeePerformanceVOResult.getWorkDelivery());
        Assertions.assertEquals(4.4, employeePerformanceVOResult.getPpeUsage());
        Assertions.assertEquals(4.0, employeePerformanceVOResult.getEvolution());
        Assertions.assertEquals(4.4, employeePerformanceVOResult.getCommitment());
        Assertions.assertEquals(month, employeePerformanceVOResult.getDate().getMonth());
        Assertions.assertEquals(year.getValue(), employeePerformanceVOResult.getDate().getYear());
        Assertions.assertEquals(month.maxLength(), employeePerformanceVOResult.getDate().getDayOfMonth());
        Assertions.assertEquals(id, employeePerformanceVOResult.getIdEmployee());
    }

    public void criaListas(List<EmployeePerformance> employeePerformanceList){
        for(EmployeePerformance employeePerformance : employeePerformanceList){
            Double adicional = 0.0;
            employeePerformance.addAttributeList(new Attribute(employeePerformance, AttributeType.PONCTUALITY, 3.0 + adicional));
            employeePerformance.addAttributeList(new Attribute(employeePerformance, AttributeType.WORK_DELIVERY, 4.0+ adicional));
            employeePerformance.addAttributeList(new Attribute(employeePerformance, AttributeType.PPE_USAGE, 5.0+ adicional));
            employeePerformance.addAttributeList(new Attribute(employeePerformance, AttributeType.EVOLUTION, 4.0+ adicional));
            employeePerformance.addAttributeList(new Attribute(employeePerformance, AttributeType.COMMITMENT, 4.0+ adicional));

            adicional += 0.1;
        }
    }
}
