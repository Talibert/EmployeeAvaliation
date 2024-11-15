package com.example.employeeperformance.services;

import com.example.employeeperformance.VOs.UpdatedAttributesVO;
import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.entities.UpdatedAttributes;
import com.example.employeeperformance.exceptions.notfound.EmployeeNotFoundException;
import com.example.employeeperformance.exceptions.notfound.EmployeeSituationAlreadySetted;
import com.example.employeeperformance.exceptions.notfound.UpdatedAttributesNotFoundException;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.repositories.UpdatedAttributesRepository;
import com.example.employeeperformance.types.AttributeType;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpdatedAttributesService {

    @Autowired
    private UpdatedAttributesRepository updatedAttributesRepository;

    /**
     * Método de busca de alteração de atributos pelo id
     * @param id
     * @return
     */
    public UpdatedAttributes findById(Long id){
        Optional<UpdatedAttributes> updatedAttributes = updatedAttributesRepository.findById(id);

        if(updatedAttributes.isPresent())
            return updatedAttributes.get();

        throw new UpdatedAttributesNotFoundException("A alteração de atributo não foi encontrada");
    }

    /**
     * Método de retorno completo
     * @return
     */
    public List<UpdatedAttributes> findAll(){
        return updatedAttributesRepository.findAll();
    }

    /**
     * Método que irá retornar uma lista de alteração de atributos pelo registro de mudanças
     * @param changesRegistry
     * @return
     */
    public List<UpdatedAttributes> findByChangesRegistry(ChangesRegistry changesRegistry){
        return updatedAttributesRepository.findByChangesRegistry(changesRegistry);
    }

    /**
     * Método para retorno uma alteração de atributo por registro de mudanças e atributo
     * @return
     */
    public UpdatedAttributes findBySetorTypeActive(ChangesRegistry changesRegistry, AttributeType attributeType){
        return updatedAttributesRepository.findByChangesRegistryAndAttributeType(changesRegistry, attributeType);
    }

    public List<UpdatedAttributes> saveALl(List<UpdatedAttributes> updatedAttributesList){
        return updatedAttributesRepository.saveAll(updatedAttributesList);
    }

    public List<UpdatedAttributesVO> getUpdatedAttributesVOList(EmployeePerformance employeePerformanceSaved, EmployeePerformance lastEmployeePerformance, ChangesRegistry changesRegistry) {
        UpdatedAttributesVO updatedAttributesVOPonctuality = new UpdatedAttributesVO(
                changesRegistry.getId(),
                AttributeType.PONCTUALITY,
                lastEmployeePerformance.getPonctuality(),
                employeePerformanceSaved.getPonctuality()
        );

        UpdatedAttributesVO updatedAttributesVOWorkDelivery = new UpdatedAttributesVO(
                changesRegistry.getId(),
                AttributeType.WORK_DELIVERY,
                lastEmployeePerformance.getWorkDelivery(),
                employeePerformanceSaved.getWorkDelivery()
        );

        UpdatedAttributesVO updatedAttributesVOPPEUsage = new UpdatedAttributesVO(
                changesRegistry.getId(),
                AttributeType.PPE_USAGE,
                lastEmployeePerformance.getPpeUsage(),
                employeePerformanceSaved.getPpeUsage()
        );

        UpdatedAttributesVO updatedAttributesVOEvolution = new UpdatedAttributesVO(
                changesRegistry.getId(),
                AttributeType.EVOLUTION,
                lastEmployeePerformance.getEvolution(),
                employeePerformanceSaved.getEvolution()
        );

        UpdatedAttributesVO updatedAttributesVOCommitment = new UpdatedAttributesVO(
                changesRegistry.getId(),
                AttributeType.COMMITMENT,
                lastEmployeePerformance.getCommitment(),
                employeePerformanceSaved.getCommitment()
        );

        return List.of(updatedAttributesVOPonctuality, updatedAttributesVOWorkDelivery, updatedAttributesVOPPEUsage,
                updatedAttributesVOEvolution, updatedAttributesVOCommitment);
    }
}
