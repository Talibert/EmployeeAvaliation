package com.example.employeeperformance.business;

import com.example.employeeperformance.VOs.ChangesRegistryVO;
import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.VOs.UpdatedAttributesVO;
import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.entities.UpdatedAttributes;
import com.example.employeeperformance.mappers.ChangesRegistryVoMapper;
import com.example.employeeperformance.mappers.EmployeePerformanceVoMapper;
import com.example.employeeperformance.services.ChangesRegistryService;
import com.example.employeeperformance.services.EmployeePerformanceService;
import com.example.employeeperformance.services.UpdatedAttributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.Year;
import java.util.List;

@Component
public class UpdatePerformance {

    @Autowired
    private EmployeePerformanceVoMapper employeePerformanceVoMapper;

    @Autowired
    private ChangesRegistryVoMapper changesRegistryVoMapper;

    @Autowired
    private EmployeePerformanceService employeePerformanceService;

    @Autowired
    private ChangesRegistryService changesRegistryService;

    @Autowired
    private UpdatedAttributesService updatedAttributesService;

    /**
     * Esse método vai receber um EmployeePerformanceVO, validar suas informações e salvá-lo
     * @param employeePerformanceVO
     */
    public void savePerformanceUpdate(EmployeePerformanceVO employeePerformanceVO){

        employeePerformanceService.validaAtributosInseridos(employeePerformanceVO);

        Year year = Year.of(employeePerformanceVO.getDate().getYear());
        Month month = Month.of(employeePerformanceVO.getDate().getMonthValue());

        EmployeePerformance employeePerformance = employeePerformanceVoMapper.getEntity(employeePerformanceVO);

        EmployeePerformance lastEmployeePerformance = employeePerformanceService.findByMesAnoEEmployeeLastRegistry(month, year, employeePerformance.getEmployee());

        EmployeePerformance employeePerformanceSaved = employeePerformanceService.saveEmployeePerformance(employeePerformance);

        saveChangesRegistry(employeePerformanceSaved, lastEmployeePerformance);
    }

    public void saveChangesRegistry(EmployeePerformance employeePerformanceSaved, EmployeePerformance lastEmployeePerformance){
        ChangesRegistryVO changesRegistryVO = new ChangesRegistryVO(
                employeePerformanceSaved.getEmployee().getId(),
                employeePerformanceSaved.getId(),
                employeePerformanceSaved.getDate()
        );

        ChangesRegistry changesRegistry = changesRegistryVoMapper.getEntity(changesRegistryVO);

        ChangesRegistry changesRegistrySaved = changesRegistryService.saveChangesRegistry(changesRegistry);

        saveUpdatedAttributes(employeePerformanceSaved, lastEmployeePerformance, changesRegistrySaved);
    }

    /**
     * TODO IMPLEMENTAR ESSE MÉTODO E OS TESTES UNITARIOS DOS NOVOS MÉTODOS CRIADOS
     * @param employeePerformanceSaved
     * @param lastEmployeePerformance
     * @param changesRegistry
     */
    public void saveUpdatedAttributes(EmployeePerformance employeePerformanceSaved, EmployeePerformance lastEmployeePerformance, ChangesRegistry changesRegistry){
        List<UpdatedAttributesVO> updatedAttributesVOList = updatedAttributesService.getUpdatedAttributesVOList(employeePerformanceSaved, lastEmployeePerformance, changesRegistry);


    }
}
