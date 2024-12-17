package com.example.employeeperformance.pontaaponta.updateperformance;

import com.example.employeeperformance.EmployeeperformanceApplication;
import com.example.employeeperformance.Fixtures.AttributeFixture;
import com.example.employeeperformance.Fixtures.EmployeeFixture;
import com.example.employeeperformance.Fixtures.EmployeePerformanceFixture;
import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.business.UpdatePerformance;
import com.example.employeeperformance.entities.*;
import com.example.employeeperformance.repositories.AbstractRepositoryTests;
import com.example.employeeperformance.repositories.AttributeRepository;
import com.example.employeeperformance.repositories.EmployeePerformanceRepository;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.services.ChangesRegistryService;
import com.example.employeeperformance.services.EmployeePerformanceService;
import com.example.employeeperformance.services.EmployeeService;
import com.example.employeeperformance.types.AttributeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

@SpringBootTest(classes = EmployeeperformanceApplication.class)
@ActiveProfiles("test")
public class PP_UpdatePerformance extends AbstractRepositoryTests {

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private EmployeePerformanceRepository employeePerformanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeePerformanceService employeePerformanceService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ChangesRegistryService changesRegistryService;

    @Autowired
    private UpdatePerformance updatePerformance;

    @BeforeEach
    public void init(){
        limpaBancos();
        resetID();
        populaBanco();
    }

    public void limpaBancos(){
        employeeRepository.deleteAll();
        attributeRepository.deleteAll();
        employeePerformanceRepository.deleteAll();
    }

    @Test
    public void testeSavePerformanceUpdate(){
        EmployeePerformanceVO employeePerformanceVO = EmployeePerformanceFixture.getEmployeePerformanceVO(LocalDate.of(2024, Month.APRIL, 23));
        employeePerformanceVO.setIdEmployee(1L);

        updatePerformance.savePerformanceUpdate(employeePerformanceVO);

        Employee employee = employeeService.findById(1L);
        EmployeePerformance employeePerformance = employeePerformanceService.findByMesAnoEEmployeeLastRegistry(Month.APRIL, Year.of(2024), employee);
        List<Attribute> attributeListSaved = employeePerformance.getAttributeList();
        Attribute attributePonctuality = attributeListSaved.stream().filter(a -> a.getAttributeType().equals(AttributeType.PONCTUALITY)).findFirst().get();
        Attribute attributeWorkDelivery = attributeListSaved.stream().filter(a -> a.getAttributeType().equals(AttributeType.WORK_DELIVERY)).findFirst().get();
        Attribute attributePPEUsage = attributeListSaved.stream().filter(a -> a.getAttributeType().equals(AttributeType.PPE_USAGE)).findFirst().get();
        Attribute attributeEvolution = attributeListSaved.stream().filter(a -> a.getAttributeType().equals(AttributeType.EVOLUTION)).findFirst().get();
        Attribute attributeCommitment = attributeListSaved.stream().filter(a -> a.getAttributeType().equals(AttributeType.COMMITMENT)).findFirst().get();

        // Assert dos atributos
        Assertions.assertEquals(employeePerformanceVO.getPonctuality(), attributePonctuality.getValue());
        Assertions.assertEquals(employeePerformanceVO.getWorkDelivery(), attributeWorkDelivery.getValue());
        Assertions.assertEquals(employeePerformanceVO.getPpeUsage(), attributePPEUsage.getValue());
        Assertions.assertEquals(employeePerformanceVO.getEvolution(), attributeEvolution.getValue());
        Assertions.assertEquals(employeePerformanceVO.getCommitment(), attributeCommitment.getValue());

        ChangesRegistry changesRegistry = changesRegistryService.findByEmployeePerformance(employeePerformance);
        List<UpdatedAttributes> updatedAttributesList = changesRegistry.getUpdatedAtributesList();

        Map<AttributeType, Double> mapUpdattedAttributes = new HashMap<>();
        updatedAttributesList.forEach(up -> mapUpdattedAttributes.put(up.getAttributeType(), up.getNewValue()));

        Assertions.assertEquals(mapUpdattedAttributes.get(AttributeType.PONCTUALITY), attributePonctuality.getValue());
        Assertions.assertEquals(mapUpdattedAttributes.get(AttributeType.WORK_DELIVERY), attributeWorkDelivery.getValue());
        Assertions.assertEquals(mapUpdattedAttributes.get(AttributeType.PPE_USAGE), attributePPEUsage.getValue());
        Assertions.assertEquals(mapUpdattedAttributes.get(AttributeType.EVOLUTION), attributeEvolution.getValue());
        Assertions.assertEquals(mapUpdattedAttributes.get(AttributeType.COMMITMENT), attributeCommitment.getValue());
    }

    private void populaBanco(){
        List<LocalDate> dates = List.of(
                LocalDate.of(2024, Month.JANUARY, 01),
                LocalDate.of(2024, Month.FEBRUARY, 01),
                LocalDate.of(2024, Month.MARCH, 01),
                LocalDate.of(2024, Month.APRIL, 01)
                );

        List<Employee> employeeList = EmployeeFixture.getEmployeeList();

        List<Employee> employeeSavedList = employeeRepository.saveAll(employeeList);

        List<EmployeePerformance> employeePerformanceList = EmployeePerformanceFixture.getEmployeePerformanceListByDates(dates, employeeSavedList);

        List<EmployeePerformance> employeePerformancesSavedList = employeePerformanceRepository.saveAll(employeePerformanceList);

        List<Attribute> attributeList = AttributeFixture.getAttributeList(employeePerformancesSavedList);

        attributeRepository.saveAll(attributeList);
    }
}
