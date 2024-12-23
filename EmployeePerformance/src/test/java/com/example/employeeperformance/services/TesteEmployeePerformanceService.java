package com.example.employeeperformance.services;

import com.example.employeeperformance.Fixtures.EmployeePerformanceFixture;
import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.calculations.PerformanceMetric;
import com.example.employeeperformance.entities.Attribute;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.exceptions.invalid.InvalidAttributeException;
import com.example.employeeperformance.repositories.EmployeePerformanceRepository;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.types.AttributeType;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesteEmployeePerformanceService {

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @Mock
    private EmployeeService employeeServiceMock;

    @Mock
    private EmployeePerformanceRepository employeePerformanceRepository;

    @Spy
    @InjectMocks
    private EmployeePerformanceService employeePerformanceServiceSpy;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeGetEmployeePerformanceAverageByYearAndMonth(){
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        Long id = 1L;

        Employee employee = new Employee("Yasuo", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.INATIVO);
        List<EmployeePerformance> employeePerformanceList = new ArrayList<>();
        Map<AttributeType, PerformanceMetric> performanceMap = new HashMap<>();
        EmployeePerformanceVO vo = new EmployeePerformanceVO();

        Mockito.when(employeeServiceMock.findById(id)).thenReturn(employee);
        Mockito.doReturn(employeePerformanceList).when(employeePerformanceServiceSpy).findByYearMonthAndEmployee(date, employee);
        Mockito.doReturn(performanceMap).when(employeePerformanceServiceSpy).popularPerformanceMap(employeePerformanceList);
        Mockito.doReturn(vo).when(employeePerformanceServiceSpy).getEmployeePerformanceAverage(performanceMap, id, date);

        employeePerformanceServiceSpy.getEmployeePerformanceAverageByYearAndMonth(date, id);

        Mockito.verify(employeePerformanceServiceSpy).findByYearMonthAndEmployee(date, employee);
        Mockito.verify(employeePerformanceServiceSpy).popularPerformanceMap(employeePerformanceList);
        Mockito.verify(employeePerformanceServiceSpy).getEmployeePerformanceAverage(performanceMap, id, date);
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
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 31);
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

        EmployeePerformanceVO employeePerformanceVOResult = employeePerformanceServiceSpy.getEmployeePerformanceAverage(performanceMap, id, date);

        Assertions.assertEquals(4.1, employeePerformanceVOResult.getPonctuality());
        Assertions.assertEquals(4.0, employeePerformanceVOResult.getWorkDelivery());
        Assertions.assertEquals(4.4, employeePerformanceVOResult.getPpeUsage());
        Assertions.assertEquals(4.0, employeePerformanceVOResult.getEvolution());
        Assertions.assertEquals(4.4, employeePerformanceVOResult.getCommitment());
        Assertions.assertEquals(date.getMonth(), employeePerformanceVOResult.getDate().getMonth());
        Assertions.assertEquals(date.getYear(), employeePerformanceVOResult.getDate().getYear());
        Assertions.assertEquals(date.getMonth().maxLength(), employeePerformanceVOResult.getDate().getDayOfMonth());
        Assertions.assertEquals(id, employeePerformanceVOResult.getIdEmployee());
    }

    @Test
    public void testeValidaAtributosInseridos(){
        EmployeePerformanceVO employeePerformanceVO = EmployeePerformanceFixture.getEmployeePerformanceVO(3.0,
                4.0, 5.0, 2.0, 1.0);

        employeePerformanceServiceSpy.validaAtributosInseridos(employeePerformanceVO);
    }

    @Test
    public void testeValidaAtributosInseridosException(){
        EmployeePerformanceVO employeePerformanceVO = EmployeePerformanceFixture.getEmployeePerformanceVO(6.0,
                4.0, 5.0, 2.0, 1.0);

        try{
            employeePerformanceServiceSpy.validaAtributosInseridos(employeePerformanceVO);
        } catch (InvalidAttributeException e){
            Assertions.assertEquals("Atributos inválidos!", e.getMessage());
        }
    }

    @Test
    public void testeIsAtributoValorValidoTrue(){
        Assertions.assertTrue(employeePerformanceServiceSpy.isAtributoValorValido(5.0));
    }

    @Test
    public void testeIsAtributoValorValidoFalse(){
        Assertions.assertFalse(employeePerformanceServiceSpy.isAtributoValorValido(6.0));
    }

    @Test
    public void testeFindByMesAnoEEmployeeLastRegistry(){
        EmployeePerformance employeePerformance1 = new EmployeePerformance();
        employeePerformance1.setId(1L);
        EmployeePerformance employeePerformance2 = new EmployeePerformance();
        employeePerformance2.setId(2L);
        EmployeePerformance employeePerformance3 = new EmployeePerformance();
        employeePerformance3.setId(3L);
        EmployeePerformance employeePerformance4 = new EmployeePerformance();
        employeePerformance4.setId(4L);
        EmployeePerformance employeePerformance5 = new EmployeePerformance();
        employeePerformance5.setId(5L);
        EmployeePerformance employeePerformance6 = new EmployeePerformance();
        employeePerformance6.setId(6L);

        Employee employee = new Employee();

        Mockito.when(employeePerformanceRepository.findByMesAnoEEmployee(1, 2024, employee)).thenReturn(
                List.of(employeePerformance1, employeePerformance2, employeePerformance3, employeePerformance4, employeePerformance5, employeePerformance6)
        );

        EmployeePerformance employeePerformanceRetornado = employeePerformanceServiceSpy.findByMesAnoEEmployeeLastRegistry(Month.JANUARY, Year.of(2024), employee);

        Assertions.assertEquals(employeePerformance6, employeePerformanceRetornado);
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
