package com.example.employeeperformance.services;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.entities.Attribute;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.exceptions.notfound.AttributesNotFoundException;
import com.example.employeeperformance.repositories.AttributeRepository;
import com.example.employeeperformance.types.AttributeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributesService {

    @Autowired
    private AttributeRepository attributesRepository;

    /**
     * Método de busca de alteração de atributos pelo id
     * @param id
     * @return
     */
    public Attribute findById(Long id){
        Optional<Attribute> attribute = attributesRepository.findById(id);

        if(attribute.isPresent())
            return attribute.get();

        throw new AttributesNotFoundException();
    }

    /**
     * Método de retorno completo
     * @return
     */
    public List<Attribute> findAll(){
        return attributesRepository.findAll();
    }

    /**
     * Método que irá retornar uma lista de atributos pelo tipo de atributo
     * @param attributeType
     * @return
     */
    public List<Attribute> findByAttributeType(AttributeType attributeType){
        return attributesRepository.findByAttributeType(attributeType);
    }

    /**
     * Método para retornar a lista de atributos associadas a performance daquele funcionário
     * @return
     */
    public List<Attribute> findByEmployeePerformance(EmployeePerformance employeePerformance){
        return attributesRepository.findByEmployeePerformance(employeePerformance);
    }

    /**
     * Método para retornar a lista de atributos associadas a performance daquele funcionário
     * @return
     */
    public Attribute findByEmployeePerformanceAndAttributeType(EmployeePerformance employeePerformance, AttributeType attributeType){
        return attributesRepository.findByEmployeePerformanceAndAttributeType(employeePerformance, attributeType);
    }

    public List<Attribute> saveAll(List<Attribute> attributesList){
        return attributesRepository.saveAll(attributesList);
    }

    public List<Attribute> montaListaDeAtributos(EmployeePerformance employeePerformance, EmployeePerformanceVO employeePerformanceVO){

        Attribute attributePonctuality = new Attribute(employeePerformance, AttributeType.PONCTUALITY, employeePerformanceVO.getPonctuality());
        Attribute attributeWorkDelivery = new Attribute(employeePerformance, AttributeType.WORK_DELIVERY, employeePerformanceVO.getWorkDelivery());
        Attribute attributePPEUsage = new Attribute(employeePerformance, AttributeType.PPE_USAGE, employeePerformanceVO.getPpeUsage());
        Attribute attributeEvolution = new Attribute(employeePerformance, AttributeType.EVOLUTION, employeePerformanceVO.getEvolution());
        Attribute attributeCommitment = new Attribute(employeePerformance, AttributeType.COMMITMENT, employeePerformanceVO.getCommitment());

        return List.of(attributePonctuality, attributeWorkDelivery, attributePPEUsage, attributeEvolution, attributeCommitment);
    }
}
