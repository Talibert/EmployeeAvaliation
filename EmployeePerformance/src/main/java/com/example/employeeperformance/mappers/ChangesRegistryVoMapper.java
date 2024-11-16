package com.example.employeeperformance.mappers;

import com.example.employeeperformance.VOs.ChangesRegistryVO;
import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.services.EmployeePerformanceService;
import com.example.employeeperformance.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangesRegistryVoMapper extends SuperMapper {

    /**
     * Metodo para converter a entidade no VO
     * @param changesRegistry
     * @return
     */
    public ChangesRegistryVO getVO(ChangesRegistry changesRegistry){
        return new ChangesRegistryVO(
                changesRegistry.getId(),
                changesRegistry.getEmployee().getId(),
                changesRegistry.getEmployeePerformance().getId(),
                changesRegistry.getUpdatedAtributesList(),
                changesRegistry.getData()
        );
    }

    /**
     * Metodo para converter o VO em uma entidade
     * @param changesRegistryVO
     * @return
     */
    public ChangesRegistry getEntity(ChangesRegistryVO changesRegistryVO){
        return new ChangesRegistry(
                getEmployee(changesRegistryVO.getIdEmployee()),
                getEmployeePerformance(changesRegistryVO.getIdEmployeePerformance()),
                changesRegistryVO.getUpdatedAttributesList(),
                changesRegistryVO.getData()
        );
    }

    /**
     * Metodo para converter uma lista de entidades em uma lista de VOs
     * @param changesRegistryList
     * @return
     */
    public List<ChangesRegistryVO> getListVO(List<ChangesRegistry> changesRegistryList){
        return changesRegistryList.stream().map(this::getVO).collect(Collectors.toList());
    }
}
