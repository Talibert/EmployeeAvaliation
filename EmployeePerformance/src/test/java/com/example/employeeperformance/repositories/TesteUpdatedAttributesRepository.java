package com.example.employeeperformance.repositories;

import com.example.employeeperformance.EmployeeperformanceApplication;
import com.example.employeeperformance.Fixtures.UpdatedAttributesFixture;
import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.UpdatedAttributes;
import com.example.employeeperformance.types.AttributeType;
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
public class TesteUpdatedAttributesRepository extends AbstractRepositoryTests{

    @Autowired
    private UpdatedAttributesRepository updatedAttributesRepository;

    @Autowired
    private ChangesRegistryRepository changesRegistryRepository;

    @BeforeEach
    public void init(){
        limpaBancos();
        resetID();
        populaBanco();
    }

    public void limpaBancos(){
        updatedAttributesRepository.deleteAll();
        changesRegistryRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testFindUpdatedAttributesById() {
        Optional<UpdatedAttributes> foundUpdatedAttributes = updatedAttributesRepository.findById(1L);

        Assertions.assertTrue(foundUpdatedAttributes.isPresent());
    }

    @Test
    @Transactional
    public void testFindUpdatedAttributesByChangesRegistry(){
        ChangesRegistry changesRegistry = changesRegistryRepository.findById(1L).get();

        List<UpdatedAttributes> listRetornada = updatedAttributesRepository.findByChangesRegistry(changesRegistry);

        listRetornada.forEach(c -> Assertions.assertEquals(changesRegistry.getId(), c.getChangesRegistry().getId()));
    }

    @Test
    @Transactional
    public void testFindUpdatedAttributesByChangesRegistryAndAttributeType(){
        ChangesRegistry changesRegistry = changesRegistryRepository.findById(1L).get();

        UpdatedAttributes updatedAttributesRetornado = updatedAttributesRepository.findByChangesRegistryAndAttributeType(changesRegistry, AttributeType.PONCTUALITY);

        Assertions.assertEquals(4, updatedAttributesRetornado.getNewValue());
        Assertions.assertEquals(5, updatedAttributesRetornado.getOldValue());
        Assertions.assertEquals(AttributeType.PONCTUALITY, updatedAttributesRetornado.getAttributeType());
    }

    private void populaBanco(){
        ChangesRegistry changesRegistry = new ChangesRegistry();
        changesRegistry.setId(1L);
        ChangesRegistry changesRegistrySaved = changesRegistryRepository.save(changesRegistry);

        List<UpdatedAttributes> updatedAttributesList = UpdatedAttributesFixture.getUpdatedAttributesList(changesRegistrySaved);

        updatedAttributesRepository.saveAll(updatedAttributesList);
    }
}
