package com.example.employeeperformance.VOs;

import java.util.ArrayList;
import java.util.List;

/**
 * Esse VO é para encapsular uma resposta para o método de busca de lista de funcionários
 */
public class EmployeeListResponseVO {

    private List<EmployeeVO> employeeVOList = new ArrayList<>();

    private String errorMessage;

    public EmployeeListResponseVO() {
    }

    public EmployeeListResponseVO(List<EmployeeVO> employeeVOList, String errorMessage) {
        this.employeeVOList = employeeVOList;
        this.errorMessage = errorMessage;
    }

    public List<EmployeeVO> getEmployeeVOList() {
        return employeeVOList;
    }

    public void setEmployeeVOList(List<EmployeeVO> employeeVOList) {
        this.employeeVOList = employeeVOList;
    }

    public void addEmployeeVOList(EmployeeVO employeeVO){
        this.employeeVOList.add(employeeVO);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
