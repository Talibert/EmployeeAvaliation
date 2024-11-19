package com.example.employeeperformance.business;

import com.example.employeeperformance.VOs.ChangesRegistryVO;
import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.VOs.UpdatedAttributesVO;
import com.example.employeeperformance.entities.Attribute;
import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.entities.UpdatedAttributes;
import com.example.employeeperformance.mappers.ChangesRegistryVoMapper;
import com.example.employeeperformance.mappers.EmployeePerformanceVoMapper;
import com.example.employeeperformance.mappers.UpdatedAttributesVoMapper;
import com.example.employeeperformance.services.AttributesService;
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
    private UpdatedAttributesVoMapper updatedAttributesVoMapper;

    @Autowired
    private EmployeePerformanceService employeePerformanceService;

    @Autowired
    private ChangesRegistryService changesRegistryService;

    @Autowired
    private UpdatedAttributesService updatedAttributesService;

    @Autowired
    private AttributesService attributesService;

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

        saveAttributes(employeePerformanceSaved, employeePerformanceVO);

        saveChangesRegistry(employeePerformanceSaved, lastEmployeePerformance);
    }

    /**
     * Esse método vai utilizar o VO para criar os atributos modificados que serão salvos no banco
     * @param employeePerformanceSaved
     * @param employeePerformanceVO
     */
    public void saveAttributes(EmployeePerformance employeePerformanceSaved, EmployeePerformanceVO employeePerformanceVO){
        List<Attribute> attributeList = attributesService.montaListaDeAtributos(employeePerformanceSaved, employeePerformanceVO);

        attributesService.saveALl(attributeList);
    }

    /**
     * Esse método vai criar um novo ChangesRegistry com base no novo EmployeePerformance que foi gerado
     * @param employeePerformanceSaved
     * @param lastEmployeePerformance
     */
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
     * Esse método vai salvar todos os atributos atualizados com base nos novos EmployeePerformance e ChangesRegistry
     * @param employeePerformanceSaved
     * @param lastEmployeePerformance
     * @param changesRegistry
     */
    public void saveUpdatedAttributes(EmployeePerformance employeePerformanceSaved, EmployeePerformance lastEmployeePerformance, ChangesRegistry changesRegistry){
        List<UpdatedAttributesVO> updatedAttributesVOList = updatedAttributesService.getUpdatedAttributesVOList(employeePerformanceSaved, lastEmployeePerformance, changesRegistry);

        List<UpdatedAttributes> updatedAttributesList = updatedAttributesVoMapper.getListEntity(updatedAttributesVOList);

        updatedAttributesService.saveALl(updatedAttributesList);
    }
}