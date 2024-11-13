package com.example.employeeperformance.repositories;

import com.example.employeeperformance.EmployeeperformanceApplication;
import com.example.employeeperformance.Fixtures.EmployeeFixture;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = EmployeeperformanceApplication.class)
@ActiveProfiles("test")
public class EmployeeRepositoryTest extends AbstractRepositoryTests{

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
    }

    @Test
    @Transactional
    public void testFindEmployeeById() {
        Optional<Employee> foundEmployee = employeeRepository.findById(1L);

        Assertions.assertEquals("Yasuo", foundEmployee.get().getNome());
    }

    @Test
    @Transactional
    public void testFindEmployeeBySituation(){
        List<Employee> listRetornada = employeeRepository.findBySituationType(SituationType.ATIVO);

        listRetornada.forEach(c -> Assertions.assertEquals(SituationType.ATIVO, c.getSituationType()));
    }

    @Test
    @Transactional
    public void testFindEmployeeBySetorType(){
        List<Employee> listRetornada = employeeRepository.findBySetorTypeAndSituationType(SetorType.OFFICE, SituationType.ATIVO);

        listRetornada.forEach(c -> Assertions.assertEquals(SetorType.OFFICE, c.getSetorType()));
        Assertions.assertEquals(2, listRetornada.size());
    }

    private void populaBanco(){
        List<Employee> employeeList = EmployeeFixture.getEmployeeList();

        employeeRepository.saveAll(employeeList);
    }
}
