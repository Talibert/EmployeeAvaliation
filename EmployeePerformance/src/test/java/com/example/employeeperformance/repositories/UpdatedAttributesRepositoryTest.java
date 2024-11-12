package com.example.employeeperformance.repositories;

import com.example.employeeperformance.EmployeeperformanceApplication;
import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.entities.UpdatedAttributes;
import com.example.employeeperformance.types.AttributeType;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = EmployeeperformanceApplication.class)
@ActiveProfiles("test")
public class UpdatedAttributesRepositoryTest {

    @Autowired
    private UpdatedAttributesRepository updatedAttributesRepository;

    @Autowired
    private ChangesRegistryRepository changesRegistryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeePerformanceRepository employeePerformanceRepository;

    private ChangesRegistry changesRegistrySaved;

    @BeforeEach
    public void init(){
        employeeRepository.deleteAll();
        updatedAttributesRepository.deleteAll();
        changesRegistryRepository.deleteAll();
        employeePerformanceRepository.deleteAll();

        Employee employee = new Employee();
        Employee employeeSaved = employeeRepository.save(employee);

        EmployeePerformance employeePerformance = new EmployeePerformance();
        employeePerformance.setEmployee(employeeSaved);
        EmployeePerformance employeePerformanceSaved = employeePerformanceRepository.save(employeePerformance);

        ChangesRegistry changesRegistry = new ChangesRegistry();
        changesRegistry.setEmployee(employeeSaved);
        changesRegistry.setEmployeePerformance(employeePerformanceSaved);
        changesRegistrySaved = changesRegistryRepository.save(changesRegistry);
    }

    @Test
    @Transactional
    public void testFindEmployeeById() {
        UpdatedAttributes updatedAttributes = new UpdatedAttributes();
        updatedAttributes.setChangesRegistry(changesRegistrySaved);

        UpdatedAttributes updatedAttributesSaved = updatedAttributesRepository.save(updatedAttributes);

        Optional<UpdatedAttributes> foundUpdatedAttributes = updatedAttributesRepository.findById(updatedAttributesSaved.getId());

        Assertions.assertTrue(foundUpdatedAttributes.isPresent());
    }

    @Test
    @Transactional
    public void testFindUpdatedAttributesByChangesRegistry(){
        UpdatedAttributes updatedAttributes1 = new UpdatedAttributes(changesRegistrySaved, AttributeType.PONCTUALITY, 5, 4);
        UpdatedAttributes updatedAttributes2 = new UpdatedAttributes(changesRegistrySaved, AttributeType.WORK_DELIVERY, 4, 3);
        UpdatedAttributes updatedAttributes3 = new UpdatedAttributes(changesRegistrySaved, AttributeType.PPE_USAGE, 3, 5);
        UpdatedAttributes updatedAttributes4 = new UpdatedAttributes(changesRegistrySaved, AttributeType.EVOLUTION, 5, 3);
        UpdatedAttributes updatedAttributes5 = new UpdatedAttributes(changesRegistrySaved, AttributeType.COMMITMENT, 2, 5);

        updatedAttributesRepository.saveAll(List.of(updatedAttributes1, updatedAttributes2, updatedAttributes3, updatedAttributes4, updatedAttributes5));

        List<UpdatedAttributes> listRetornada = updatedAttributesRepository.findByChangesRegistry(changesRegistrySaved);

        listRetornada.forEach(c -> Assertions.assertEquals(changesRegistrySaved.getId(), c.getChangesRegistry().getId()));
    }

    @Test
    @Transactional
    public void testFindUpdatedAttributesByChangesRegistryAndAttributeType(){
        UpdatedAttributes updatedAttributes1 = new UpdatedAttributes(changesRegistrySaved, AttributeType.PONCTUALITY, 5, 4);
        UpdatedAttributes updatedAttributes2 = new UpdatedAttributes(changesRegistrySaved, AttributeType.WORK_DELIVERY, 4, 3);
        UpdatedAttributes updatedAttributes3 = new UpdatedAttributes(changesRegistrySaved, AttributeType.PPE_USAGE, 3, 5);
        UpdatedAttributes updatedAttributes4 = new UpdatedAttributes(changesRegistrySaved, AttributeType.EVOLUTION, 5, 3);
        UpdatedAttributes updatedAttributes5 = new UpdatedAttributes(changesRegistrySaved, AttributeType.COMMITMENT, 2, 5);

        updatedAttributesRepository.saveAll(List.of(updatedAttributes1, updatedAttributes2, updatedAttributes3, updatedAttributes4, updatedAttributes5));

        UpdatedAttributes updatedAttributesRetornado = updatedAttributesRepository.findByChangesRegistryAndAttributeType(changesRegistrySaved, AttributeType.PONCTUALITY);

        Assertions.assertEquals(updatedAttributes1.getNewValue(), updatedAttributesRetornado.getNewValue());
        Assertions.assertEquals(updatedAttributes1.getOldValue(), updatedAttributesRetornado.getOldValue());
        Assertions.assertEquals(AttributeType.PONCTUALITY, updatedAttributesRetornado.getAttributeType());
    }
}
