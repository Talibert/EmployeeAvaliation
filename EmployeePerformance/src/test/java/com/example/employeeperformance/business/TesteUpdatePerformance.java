package com.example.employeeperformance.business;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.mappers.ChangesRegistryVoMapper;
import com.example.employeeperformance.mappers.EmployeePerformanceVoMapper;
import com.example.employeeperformance.mappers.UpdatedAttributesVoMapper;
import com.example.employeeperformance.services.AttributesService;
import com.example.employeeperformance.services.ChangesRegistryService;
import com.example.employeeperformance.services.EmployeePerformanceService;
import com.example.employeeperformance.services.UpdatedAttributesService;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.Month;

class TesteUpdatePerformance {

    @Mock
    private EmployeePerformanceVoMapper employeePerformanceVoMapper;

    @Mock
    private ChangesRegistryVoMapper changesRegistryVoMapper;

    @Mock
    private UpdatedAttributesVoMapper updatedAttributesVoMapper;

    @Mock
    private EmployeePerformanceService employeePerformanceService;

    @Mock
    private AttributesService attributesService;

    @Mock
    private ChangesRegistryService changesRegistryService;

    @Mock
    private UpdatedAttributesService updatedAttributesService;

    @Spy
    @InjectMocks
    private UpdatePerformance updatePerformance;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeSavePerformanceUpdate() {
        EmployeePerformanceVO employeePerformanceVO = new EmployeePerformanceVO();
        EmployeePerformance lastEmployeePerformance = new EmployeePerformance();
        EmployeePerformance savedEmployeePerformance = new EmployeePerformance();
        Pair<EmployeePerformance, EmployeePerformance> employeePerformancePair = Pair.of(savedEmployeePerformance, lastEmployeePerformance);

        Mockito.doReturn(employeePerformancePair).when(updatePerformance).getEmployeePerformancePair(employeePerformanceVO);
        Mockito.doNothing().when(updatePerformance).saveAttributes(savedEmployeePerformance, employeePerformanceVO);
        Mockito.doNothing().when(updatePerformance).saveChangesRegistry(employeePerformancePair);

        updatePerformance.savePerformanceUpdate(employeePerformanceVO);

        Mockito.verify(updatePerformance).saveAttributes(savedEmployeePerformance, employeePerformanceVO);
        Mockito.verify(updatePerformance).saveChangesRegistry(employeePerformancePair);
    }

    @Test
    void testeSaveAttributes() {
        EmployeePerformance employeePerformance = new EmployeePerformance();
        EmployeePerformanceVO employeePerformanceVO = new EmployeePerformanceVO();

        updatePerformance.saveAttributes(employeePerformance, employeePerformanceVO);

        Mockito.verify(attributesService).montaListaDeAtributos(employeePerformance, employeePerformanceVO);
        Mockito.verify(attributesService).saveAll(Mockito.any());
    }

    @Test
    void testeSaveChangesRegistry() {
        Employee employee = new Employee();
        EmployeePerformance savedEmployeePerformance = new EmployeePerformance();
        savedEmployeePerformance.setEmployee(employee);
        EmployeePerformance lastEmployeePerformance = new EmployeePerformance();
        Pair<EmployeePerformance, EmployeePerformance> employeePerformancePair = Pair.of(savedEmployeePerformance, lastEmployeePerformance);


        Mockito.doNothing().when(updatePerformance).saveUpdatedAttributes(Mockito.any(), Mockito.any());

        updatePerformance.saveChangesRegistry(employeePerformancePair);

        Mockito.verify(changesRegistryVoMapper).getEntity(Mockito.any());
        Mockito.verify(changesRegistryService).saveChangesRegistry(Mockito.any());
        Mockito.verify(updatePerformance).saveUpdatedAttributes(Mockito.any(), Mockito.any());
    }

    @Test
    void testeSaveUpdatedAttributes() {
        EmployeePerformance savedEmployeePerformance = new EmployeePerformance();
        EmployeePerformance lastEmployeePerformance = new EmployeePerformance();
        Pair<EmployeePerformance, EmployeePerformance> employeePerformancePair = Pair.of(savedEmployeePerformance, lastEmployeePerformance);
        ChangesRegistry changesRegistry = new ChangesRegistry();

        updatePerformance.saveUpdatedAttributes(employeePerformancePair, changesRegistry);

        Mockito.verify(updatedAttributesService).getUpdatedAttributesVOList(savedEmployeePerformance, lastEmployeePerformance, changesRegistry);
        Mockito.verify(updatedAttributesVoMapper).getListEntity(Mockito.any());
        Mockito.verify(updatedAttributesService).saveAll(Mockito.any());
    }

    @Test
    void testeGetEmployeePerformancePair() {
        LocalDate data = LocalDate.of(2024, Month.JANUARY, 1);
        Employee employee = new Employee();
        employee.setId(1L);
        EmployeePerformanceVO employeePerformanceVO = new EmployeePerformanceVO(1L, data, 4.0, 3.0, 5.0, 4.0, 3.0);
        EmployeePerformance lastEmployeePerformance = new EmployeePerformance(data, employee);
        lastEmployeePerformance.setId(1L);
        EmployeePerformance savedEmployeePerformance = new EmployeePerformance(data, employee);
        lastEmployeePerformance.setId(2L);

        Mockito.when(employeePerformanceVoMapper.getEntity(employeePerformanceVO)).thenCallRealMethod();
        Mockito.doReturn(lastEmployeePerformance).when(employeePerformanceService).findByMesAnoEEmployeeLastRegistry(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doReturn(lastEmployeePerformance).when(employeePerformanceService).saveEmployeePerformance(Mockito.any());

        Pair<EmployeePerformance, EmployeePerformance> employeePerformancePair = updatePerformance.getEmployeePerformancePair(employeePerformanceVO);

        Assertions.assertEquals(savedEmployeePerformance, employeePerformancePair.getLeft());
        Assertions.assertEquals(lastEmployeePerformance, employeePerformancePair.getRight());

        Mockito.verify(employeePerformanceVoMapper).getEntity(employeePerformanceVO);
        Mockito.verify(employeePerformanceService).findByMesAnoEEmployeeLastRegistry(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(employeePerformanceService).saveEmployeePerformance(Mockito.any());
    }
}