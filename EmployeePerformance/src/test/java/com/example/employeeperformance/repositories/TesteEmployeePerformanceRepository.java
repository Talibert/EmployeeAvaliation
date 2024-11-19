package com.example.employeeperformance.repositories;

import com.example.employeeperformance.EmployeeperformanceApplication;
import com.example.employeeperformance.Fixtures.EmployeeFixture;
import com.example.employeeperformance.Fixtures.EmployeePerformanceFixture;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;

@SpringBootTest(classes = EmployeeperformanceApplication.class)
@ActiveProfiles("test")
public class TesteEmployeePerformanceRepository extends AbstractRepositoryTests {

    @Autowired
    private EmployeePerformanceRepository employeePerformanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void init() {
        limpaBancos();
        resetID();
        populaBanco();
    }

    public void limpaBancos(){
        employeeRepository.deleteAll();
        employeePerformanceRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testeFindById() {
        Employee employee = employeeRepository.save(new Employee("John", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.ATIVO));
        LocalDate date = LocalDate.of(2024, Month.DECEMBER, 10);
        EmployeePerformance employeePerformance = new EmployeePerformance(date, employee);

        EmployeePerformance savedEmployeePerformance = employeePerformanceRepository.save(employeePerformance);

        EmployeePerformance foundEmployeePerformance = employeePerformanceRepository.findById(savedEmployeePerformance.getId()).get();

        Assertions.assertEquals(date, foundEmployeePerformance.getDate());
    }

    @Test
    @Transactional
    public void testeFindByEmployeeId(){
        Employee employee = employeeRepository.findByNome("Yasuo");

        List<EmployeePerformance> employeePerformanceList = employeePerformanceRepository.findByEmployee(employee);

        Assertions.assertEquals(6, employeePerformanceList.size());
        employeePerformanceList.forEach(e -> Assertions.assertEquals(employee, e.getEmployee()));
    }

    @Test
    @Transactional
    public void testeFindByMesEAno(){
        List<EmployeePerformance> employeePerformanceList = employeePerformanceRepository.findByMesEAno(1, 2024);

        LocalDate dateJanuary = LocalDate.of(2024, Month.JANUARY, 10);

        employeePerformanceList.forEach(ep -> Assertions.assertEquals(dateJanuary, ep.getDate()));
        Assertions.assertEquals(7, employeePerformanceList.size());
    }

    @Test
    @Transactional
    public void testeFindByMesAnoEEmployee(){
        Employee employee = employeeRepository.findByNome("Yasuo");

        List<EmployeePerformance> employeePerformanceList = employeePerformanceRepository.findByMesAnoEEmployee(1, 2024, employee);

        LocalDate dateJanuary = LocalDate.of(2024, Month.JANUARY, 10);

        employeePerformanceList.forEach(ep -> Assertions.assertEquals(dateJanuary, ep.getDate()));
        employeePerformanceList.forEach(ep -> Assertions.assertEquals("Yasuo", ep.getEmployee().getNome()));
        Assertions.assertEquals(1, employeePerformanceList.size());
    }

    private void populaBanco(){
        List<Employee> employees = EmployeeFixture.getEmployeeList();
        employeeRepository.saveAll(employees);
        List<Employee> employeesSaved = employeeRepository.findAll();

        LocalDate dateJanuary = LocalDate.of(2024, Month.JANUARY, 10);
        LocalDate dateFebruary = LocalDate.of(2024, Month.FEBRUARY, 10);
        LocalDate dateMarch = LocalDate.of(2024, Month.MARCH, 10);
        LocalDate dateApril = LocalDate.of(2024, Month.APRIL, 10);
        LocalDate dateMay = LocalDate.of(2024, Month.MAY, 10);
        LocalDate dateJune = LocalDate.of(2024, Month.JUNE, 10);

        List<LocalDate> datas = List.of(dateJanuary, dateFebruary, dateMarch, dateApril, dateMay, dateJune);

        List<EmployeePerformance> employeePerformanceList = EmployeePerformanceFixture.getEmployeePerformanceListByDates(datas, employeesSaved);

        employeePerformanceRepository.saveAll(employeePerformanceList);
    }
}
