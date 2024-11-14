package com.example.employeeperformance.business;

import com.example.employeeperformance.VOs.ChangesRegistryVO;
import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.VOs.UpdatedAttributesVO;
import com.example.employeeperformance.mappers.EmployeePerformanceVoMapper;
import com.example.employeeperformance.services.EmployeePerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePerformance {

    @Autowired
    private EmployeePerformanceVoMapper employeePerformanceVoMapper;

    @Autowired
    private EmployeePerformanceService employeePerformanceService;

    /**
     * Esse método vai receber um EmployeePerformanceVO, validar suas informações e salvá-lo
     * @param employeePerformanceVO
     */
    public void savePerformanceUpdate(EmployeePerformanceVO employeePerformanceVO){

        employeePerformanceService.validaAtributosInseridos(employeePerformanceVO);


    }
}
