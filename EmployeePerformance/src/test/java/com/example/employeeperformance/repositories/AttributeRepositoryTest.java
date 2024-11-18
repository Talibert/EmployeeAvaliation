package com.example.employeeperformance.repositories;

import com.example.employeeperformance.EmployeeperformanceApplication;
import com.example.employeeperformance.Fixtures.AttributeFixture;
import com.example.employeeperformance.Fixtures.EmployeeFixture;
import com.example.employeeperformance.Fixtures.EmployeePerformanceFixture;
import com.example.employeeperformance.Fixtures.UpdatedAttributesFixture;
import com.example.employeeperformance.entities.*;
import com.example.employeeperformance.types.AttributeType;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = EmployeeperformanceApplication.class)
@ActiveProfiles("test")
public class AttributeRepositoryTest extends AbstractRepositoryTests{

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private EmployeePerformanceRepository employeePerformanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void init(){
        limpaBancos();
        resetID();
        populaBanco();
    }

    public void limpaBancos(){
        attributeRepository.deleteAll();
        employeePerformanceRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testFindAttributesById() {
        Optional<Attribute> foundAttribute = attributeRepository.findById(1L);

        Assertions.assertTrue(foundAttribute.isPresent());
    }

    @Test
    @Transactional
    public void testFindAttributesByAttributeType() {
        List<Attribute> foundAttributesPonctuality = attributeRepository.findByAttributeType(AttributeType.PONCTUALITY);

        foundAttributesPonctuality.forEach(a -> Assertions.assertEquals(AttributeType.PONCTUALITY, a.getAttributeType()));
        Assertions.assertEquals(28, foundAttributesPonctuality.size());

        List<Attribute> foundAttributesWorkDelivery = attributeRepository.findByAttributeType(AttributeType.WORK_DELIVERY);

        foundAttributesWorkDelivery.forEach(a -> Assertions.assertEquals(AttributeType.WORK_DELIVERY, a.getAttributeType()));
        Assertions.assertEquals(28, foundAttributesWorkDelivery.size());

        List<Attribute> foundAttributesPPEUsage = attributeRepository.findByAttributeType(AttributeType.PPE_USAGE);

        foundAttributesPPEUsage.forEach(a -> Assertions.assertEquals(AttributeType.PPE_USAGE, a.getAttributeType()));
        Assertions.assertEquals(28, foundAttributesPPEUsage.size());

        List<Attribute> foundAttributesEvolution = attributeRepository.findByAttributeType(AttributeType.EVOLUTION);

        foundAttributesEvolution.forEach(a -> Assertions.assertEquals(AttributeType.EVOLUTION, a.getAttributeType()));
        Assertions.assertEquals(28, foundAttributesEvolution.size());

        List<Attribute> foundAttributesCommitment = attributeRepository.findByAttributeType(AttributeType.COMMITMENT);

        foundAttributesCommitment.forEach(a -> Assertions.assertEquals(AttributeType.COMMITMENT, a.getAttributeType()));
        Assertions.assertEquals(28, foundAttributesCommitment.size());
    }

    @Test
    @Transactional
    public void testeFindByEmployeePerformance(){
        EmployeePerformance employeePerformance = employeePerformanceRepository.findById(1L).get();

        List<Attribute> foundAttributes = attributeRepository.findByEmployeePerformance(employeePerformance);

        foundAttributes.forEach(a -> Assertions.assertEquals(employeePerformance, a.getEmployeePerformance()));
        Assertions.assertEquals(5, foundAttributes.size());
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
