package com.example.employeeperformance.repositories;

import com.example.employeeperformance.EmployeeperformanceApplication;
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

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@SpringBootTest(classes = EmployeeperformanceApplication.class)
@ActiveProfiles("test")
public class EmployeePerformanceRepositoryTest {

    @Autowired
    private EmployeePerformanceRepository employeePerformanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void limpaBanco() {
        employeeRepository.deleteAll();
        employeePerformanceRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testeFindById() {
        Employee employee = employeeRepository.save(new Employee("John", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.ATIVO));
        LocalDateTime date = LocalDateTime.of(2024, Month.DECEMBER, 10, 0, 0, 0);
        EmployeePerformance employeePerformance = new EmployeePerformance(date, employee, 5.0, 5.0, 5.0, 5.0, 5.0);

        EmployeePerformance savedEmployeePerformance = employeePerformanceRepository.save(employeePerformance);

        EmployeePerformance foundEmployeePerformance = employeePerformanceRepository.findById(savedEmployeePerformance.getId()).get();

        Assertions.assertEquals(date, foundEmployeePerformance.getDate());
        Assertions.assertEquals(5, foundEmployeePerformance.getPonctuality());
        Assertions.assertEquals(5, foundEmployeePerformance.getWorkDelivery());
        Assertions.assertEquals(5, foundEmployeePerformance.getPpeUsage());
        Assertions.assertEquals(5, foundEmployeePerformance.getEvolution());
        Assertions.assertEquals(5, foundEmployeePerformance.getCommitment());
    }

    @Test
    @Transactional
    public void testeFindByEmployeeId(){
        populaBanco();

        Employee employee = employeeRepository.findByNome("Yasuo");

        List<EmployeePerformance> employeePerformanceList = employeePerformanceRepository.findByEmployee(employee);

        Assertions.assertEquals(6, employeePerformanceList.size());
        employeePerformanceList.forEach(e -> Assertions.assertEquals(employee, e.getEmployee()));
    }

    @Test
    @Transactional
    public void testeFindByMesEAno(){
        populaBanco();

        List<EmployeePerformance> employeePerformanceList = employeePerformanceRepository.findByMesEAno(1, 2024);

        LocalDateTime dateJanuary = LocalDateTime.of(2024, Month.JANUARY, 10, 0, 0, 0);

        employeePerformanceList.forEach(ep -> Assertions.assertEquals(dateJanuary, ep.getDate()));
        Assertions.assertEquals(7, employeePerformanceList.size());
    }

    @Test
    @Transactional
    public void testeFindByMesAnoEEmployee(){
        populaBanco();

        Employee employee = employeeRepository.findByNome("Yasuo");

        List<EmployeePerformance> employeePerformanceList = employeePerformanceRepository.findByMesAnoEEmployee(1, 2024, employee);

        LocalDateTime dateJanuary = LocalDateTime.of(2024, Month.JANUARY, 10, 0, 0, 0);

        employeePerformanceList.forEach(ep -> Assertions.assertEquals(dateJanuary, ep.getDate()));
        employeePerformanceList.forEach(ep -> Assertions.assertEquals("Yasuo", ep.getEmployee().getNome()));
        Assertions.assertEquals(1, employeePerformanceList.size());
    }

    private void populaBanco(){
        Employee employee1 = new Employee("Yasuo", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.ATIVO);
        Employee employee2 = new Employee("Kassadin", "222.222.222-22", "Observação", SetorType.STOCK, SituationType.INATIVO);
        Employee employee3 = new Employee("Caitlyn", "333.333.333-33", "Observação", SetorType.OTHERS, SituationType.ATIVO);
        Employee employee4 = new Employee("Vi", "444.444.444-44", "Observação", SetorType.OFFICE, SituationType.INATIVO);
        Employee employee5 = new Employee("Ezreal", "555.555.555-55", "Observação", SetorType.OFFICE, SituationType.ATIVO);
        Employee employee6 = new Employee("Aphelios", "666.666.666-66", "Observação", SetorType.OTHERS, SituationType.INATIVO);
        Employee employee7 = new Employee("Leona", "777.777.777-77", "Observação", SetorType.STOCK, SituationType.ATIVO);

        List<Employee> employees = List.of(employee1, employee2, employee3, employee4, employee5, employee6, employee7);
        employeeRepository.saveAll(employees);
        List<Employee> employeesRecuperados = employeeRepository.findAll();

        LocalDateTime dateJanuary = LocalDateTime.of(2024, Month.JANUARY, 10, 0, 0, 0);
        LocalDateTime dateFebruary = LocalDateTime.of(2024, Month.FEBRUARY, 10, 0, 0, 0);
        LocalDateTime dateMarch = LocalDateTime.of(2024, Month.MARCH, 10, 0, 0, 0);
        LocalDateTime dateApril = LocalDateTime.of(2024, Month.APRIL, 10, 0, 0, 0);
        LocalDateTime dateMay = LocalDateTime.of(2024, Month.MAY, 10, 0, 0, 0);
        LocalDateTime dateJune = LocalDateTime.of(2024, Month.JUNE, 10, 0, 0, 0);

        List<LocalDateTime> datas = List.of(dateJanuary, dateFebruary, dateMarch, dateApril, dateMay, dateJune);

        for(Employee employee : employeesRecuperados){
            for(LocalDateTime data : datas){
                EmployeePerformance employeePerformance = new EmployeePerformance(data, employee, 5.0, 5.0, 5.0, 5.0, 5.0);
                employeePerformanceRepository.save(employeePerformance);
            }
        }
    }
}
