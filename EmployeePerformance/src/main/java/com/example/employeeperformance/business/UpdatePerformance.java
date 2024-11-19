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
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.Year;
import java.util.List;

import static org.springframework.data.util.Pair.of;

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

        Pair<EmployeePerformance, EmployeePerformance> employeePerformancePair = getEmployeePerformancePair(employeePerformanceVO);

        saveAttributes(employeePerformancePair.getLeft(), employeePerformanceVO);

        saveChangesRegistry(employeePerformancePair);
    }

    /**
     * Esse método vai utilizar o VO para criar os atributos modificados que serão salvos no banco
     * @param savedEmployeePerformance
     * @param employeePerformanceVO
     */
    public void saveAttributes(EmployeePerformance savedEmployeePerformance, EmployeePerformanceVO employeePerformanceVO){
        List<Attribute> attributeList = attributesService.montaListaDeAtributos(savedEmployeePerformance, employeePerformanceVO);

        attributesService.saveAll(attributeList);
    }

    /**
     * Esse método vai criar um novo ChangesRegistry com base no novo EmployeePerformance que foi gerado
     * @param employeePerformancePair
     */
    public void saveChangesRegistry(Pair<EmployeePerformance, EmployeePerformance> employeePerformancePair){
        ChangesRegistryVO changesRegistryVO = new ChangesRegistryVO(
                employeePerformancePair.getLeft().getEmployee().getId(),
                employeePerformancePair.getLeft().getId(),
                employeePerformancePair.getLeft().getDate()
        );

        ChangesRegistry changesRegistry = changesRegistryVoMapper.getEntity(changesRegistryVO);

        ChangesRegistry changesRegistrySaved = changesRegistryService.saveChangesRegistry(changesRegistry);

        saveUpdatedAttributes(employeePerformancePair, changesRegistrySaved);
    }

    /**
     * Esse método vai salvar todos os atributos atualizados com base nos novos EmployeePerformance e ChangesRegistry
     * @param employeePerformancePair
     * @param changesRegistry
     */
    public void saveUpdatedAttributes(Pair<EmployeePerformance, EmployeePerformance> employeePerformancePair, ChangesRegistry changesRegistry){
        List<UpdatedAttributesVO> updatedAttributesVOList = updatedAttributesService.getUpdatedAttributesVOList(employeePerformancePair.getLeft(), employeePerformancePair.getRight(), changesRegistry);

        List<UpdatedAttributes> updatedAttributesList = updatedAttributesVoMapper.getListEntity(updatedAttributesVOList);

        updatedAttributesService.saveALl(updatedAttributesList);
    }

    /**
     * TODO Verificar a possibilidade de buscar em meses anteriores e, caso seja realmente o primeiro registro, saber lidar com isso
     * Esse método irá recuperar o último registro de performance do funcionário, salvar o atual e colocar os dois em um pair
     * O atual é o da esquerda e o ultimo registro encontrado no banco é o da direita
     * @param employeePerformanceVO
     * @return
     */
    public Pair<EmployeePerformance, EmployeePerformance> getEmployeePerformancePair(EmployeePerformanceVO employeePerformanceVO){
        Year year = Year.of(employeePerformanceVO.getDate().getYear());
        Month month = Month.of(employeePerformanceVO.getDate().getMonthValue());

        EmployeePerformance employeePerformance = employeePerformanceVoMapper.getEntity(employeePerformanceVO);

        EmployeePerformance lastEmployeePerformance = employeePerformanceService.findByMesAnoEEmployeeLastRegistry(month, year, employeePerformance.getEmployee());

        EmployeePerformance savedEmployeePerformance = employeePerformanceService.saveEmployeePerformance(employeePerformance);

        return Pair.of(savedEmployeePerformance, lastEmployeePerformance);
    }
}
