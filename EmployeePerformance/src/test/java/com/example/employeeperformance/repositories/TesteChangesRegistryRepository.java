package com.example.employeeperformance.repositories;

import com.example.employeeperformance.EmployeeperformanceApplication;
import com.example.employeeperformance.Fixtures.ChangesRegistryFixture;
import com.example.employeeperformance.Fixtures.EmployeeFixture;
import com.example.employeeperformance.Fixtures.EmployeePerformanceFixture;
import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootTest(classes = EmployeeperformanceApplication.class)
@ActiveProfiles("test")
public class TesteChangesRegistryRepository extends AbstractRepositoryTests {

    @Autowired
    private ChangesRegistryRepository changesRegistryRepository;

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
        changesRegistryRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testeFindById() {
        ChangesRegistry changesRegistry = changesRegistryRepository.findById(1L).get();

        Assertions.assertEquals("Yasuo", changesRegistry.getEmployee().getNome());
        Assertions.assertEquals("Yasuo", changesRegistry.getEmployeePerformance().getEmployee().getNome());
    }

    @Test
    @Transactional
    public void testeFindAll(){
        List<ChangesRegistry> changesRegistryList = changesRegistryRepository.findAll();

        Assertions.assertEquals(42, changesRegistryList.size());
    }

    @Test
    @Transactional
    public void findByEmployee(){
        Employee employee = employeeRepository.findByNome("Yasuo");

        List<ChangesRegistry> changesRegistryList = changesRegistryRepository.findByEmployee(employee);

        Assertions.assertEquals(6, changesRegistryList.size());
    }

    @Test
    @Transactional
    public void findByEmployeePerformance(){
        EmployeePerformance employeePerformance = employeePerformanceRepository.findById(1L).get();

        List<ChangesRegistry> changesRegistryList = changesRegistryRepository.findByEmployeePerformance(employeePerformance);

        Assertions.assertEquals(1, changesRegistryList.size());
    }

    @Test
    @Transactional
    public void findLastChangesRegistryByEmployee(){
        Employee employee = employeeRepository.findById(1L).get();
        LocalDate data = LocalDate.of(2024, Month.JUNE, 10);

        ChangesRegistry changesRegistry1 = new ChangesRegistry();
        changesRegistry1.setEmployee(employee);
        changesRegistry1.setData(data);
        ChangesRegistry changesRegistry2 = new ChangesRegistry();
        changesRegistry2.setEmployee(employee);
        changesRegistry2.setData(data);
        ChangesRegistry changesRegistry3 = new ChangesRegistry();
        changesRegistry3.setEmployee(employee);
        changesRegistry3.setData(data);
        ChangesRegistry changesRegistry4 = new ChangesRegistry();
        changesRegistry4.setEmployee(employee);
        changesRegistry4.setData(data);

        List<ChangesRegistry> novosRegistros = List.of(changesRegistry1, changesRegistry2, changesRegistry3, changesRegistry4);

        Long idEsperado = (long) changesRegistryRepository.findAll().size() + novosRegistros.size();

        changesRegistryRepository.saveAll(novosRegistros);

        ChangesRegistry changesRegistry = changesRegistryRepository.findLastChangesRegistryByEmployee(employee);

        Assertions.assertEquals(idEsperado, changesRegistry.getId());
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

        List<EmployeePerformance> employeePerformancesSaved = employeePerformanceRepository.saveAll(employeePerformanceList);

        List<ChangesRegistry> changesRegistryList = ChangesRegistryFixture.getChangesRegistryListByEmployeePerformance(employeePerformancesSaved);

        changesRegistryRepository.saveAll(changesRegistryList);
    }
}
