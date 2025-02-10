package com.example.employeeperformance.mappers;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.VOs.UpdatedAttributesVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.entities.UpdatedAttributes;
import com.example.employeeperformance.services.ChangesRegistryService;
import com.example.employeeperformance.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UpdatedAttributesVoMapper extends SuperMapper{

    /**
     * Metodo para converter a entidade no VO
     * @param updatedAttributes
     * @return
     */
    public UpdatedAttributesVO getVO(UpdatedAttributes updatedAttributes){
        return new UpdatedAttributesVO(
                updatedAttributes.getId(),
                updatedAttributes.getChangesRegistry().getId(),
                updatedAttributes.getAttributeType(),
                updatedAttributes.getOldValue(),
                updatedAttributes.getNewValue());
    }

    /**
     * Metodo para converter o VO em uma entidade
     * @param updatedAttributesVO
     * @return
     */
    public UpdatedAttributes getEntity(UpdatedAttributesVO updatedAttributesVO){
        return new UpdatedAttributes(
                getChangesRegistry(updatedAttributesVO.getIdChangesRegistry()),
                updatedAttributesVO.getAttributeType(),
                updatedAttributesVO.getOldValue(),
                updatedAttributesVO.getNewValue()
        );
    }

    /**
     * Metodo para converter uma lista de entidades em uma lista de VOs
     * @param updatedAttributesList
     * @return
     */
    public List<UpdatedAttributesVO> getListVO(List<UpdatedAttributes> updatedAttributesList){
        return updatedAttributesList.stream().map(this::getVO).collect(Collectors.toList());
    }

    /**
     * Metodo para converter uma lista de VOs em uma lista de entidades
     * @param updatedAttributesVOList
     * @return
     */
    public List<UpdatedAttributes> getListEntity(List<UpdatedAttributesVO> updatedAttributesVOList){
        return updatedAttributesVOList.stream().map(this::getEntity).collect(Collectors.toList());
    }
}
