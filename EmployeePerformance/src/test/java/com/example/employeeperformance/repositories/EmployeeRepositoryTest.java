package com.example.employeeperformance.repositories;

import com.example.employeeperformance.EmployeeperformanceApplication;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = EmployeeperformanceApplication.class)
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void limpaBanco() {
        employeeRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testFindEmployeeById() {
        Employee employee = new Employee("John", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.ATIVO);

        Employee savedEmployee = employeeRepository.save(employee);

        Optional<Employee> foundEmployee = employeeRepository.findById(savedEmployee.getId());

        Assertions.assertEquals("John", foundEmployee.get().getNome());
    }

    @Test
    @Transactional
    public void testFindEmployeeBySituation(){
        createEmployees();

        List<Employee> listRetornada = employeeRepository.findBySituationType(SituationType.ATIVO);

        listRetornada.forEach(c -> Assertions.assertEquals(SituationType.ATIVO, c.getSituationType()));
    }

    @Test
    @Transactional
    public void testFindEmployeeBySetorType(){
        createEmployees();

        List<Employee> listRetornada = employeeRepository.findBySetorTypeAndSituationType(SetorType.OFFICE, SituationType.ATIVO);

        listRetornada.forEach(c -> Assertions.assertEquals(SetorType.OFFICE, c.getSetorType()));
        Assertions.assertEquals(2, listRetornada.size());
    }

    /**
     * Método que cria uma série de funcionários para os cenários de testes
     */
    private void createEmployees(){
        Employee employee = new Employee("Yasuo", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.ATIVO);
        Employee employee2 = new Employee("Kassadin", "222.222.222-22", "Observação", SetorType.STOCK, SituationType.INATIVO);
        Employee employee3 = new Employee("Caitlyn", "333.333.333-33", "Observação", SetorType.OTHERS, SituationType.ATIVO);
        Employee employee4 = new Employee("Vi", "444.444.444-44", "Observação", SetorType.OFFICE, SituationType.INATIVO);
        Employee employee5 = new Employee("Ezreal", "555.555.555-55", "Observação", SetorType.OFFICE, SituationType.ATIVO);
        Employee employee6 = new Employee("Aphelios", "666.666.666-66", "Observação", SetorType.OTHERS, SituationType.INATIVO);
        Employee employee7 = new Employee("Leona", "777.777.777-77", "Observação", SetorType.STOCK, SituationType.ATIVO);
        employee.setId(1L);
        employee2.setId(2L);
        employee3.setId(3L);
        employee4.setId(4L);
        employee5.setId(5L);
        employee6.setId(6L);
        employee7.setId(7L);

        employeeRepository.saveAll(List.of(employee, employee2, employee3, employee4, employee5, employee6, employee7));
    }
}
