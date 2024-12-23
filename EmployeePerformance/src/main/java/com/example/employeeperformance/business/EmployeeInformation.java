package com.example.employeeperformance.business;

import com.example.employeeperformance.VOs.ChangesRegistryVO;
import com.example.employeeperformance.VOs.EmployeeVO;

import java.util.List;

/**
 * Esse objeto vai encapsular as informações quando detalharmos um funcionário na tela
 */
public class EmployeeInformation {

    /**
     * Funcionário
     */
    private EmployeeVO employeeVO;

    /**
     * Informações sobre o resumo das performances do funcionário
     */
    private PerformanceResume performanceResume;

    /**
     * lista com os últimos registros de mudanças
     */
    private List<ChangesRegistryVO> changesRegistryVO;

    public EmployeeInformation(){

    }

    public EmployeeVO getEmployeeVO() {
        return employeeVO;
    }

    public void setEmployeeVO(EmployeeVO employeeVO) {
        this.employeeVO = employeeVO;
    }

    public PerformanceResume getPerformanceResume() {
        return performanceResume;
    }

    public void setPerformanceResume(PerformanceResume performanceResume) {
        this.performanceResume = performanceResume;
    }

    public List<ChangesRegistryVO> getChangesRegistryVO() {
        return changesRegistryVO;
    }

    public void setChangesRegistryVO(List<ChangesRegistryVO> changesRegistryVO) {
        this.changesRegistryVO = changesRegistryVO;
    }
}
