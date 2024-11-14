package com.example.employeeperformance.VOs;

import com.example.employeeperformance.entities.UpdatedAttributes;

import java.time.LocalDate;
import java.util.List;

public class ChangesRegistryVO {

    private Long id;

    private Long idEmployee;

    private Long idEmployeePerformance;

    private List<UpdatedAttributes> updatedAttributesList;

    private LocalDate data;

    public ChangesRegistryVO(){
    }

    public ChangesRegistryVO(Long idEmployee, Long idEmployeePerformance, List<UpdatedAttributes> updatedAttributesList, LocalDate date) {
        this.idEmployee = idEmployee;
        this.idEmployeePerformance = idEmployeePerformance;
        this.updatedAttributesList = updatedAttributesList;
        this.data = date;
    }

    public ChangesRegistryVO(Long id, Long idEmployee, Long idEmployeePerformance, List<UpdatedAttributes> updatedAttributesList, LocalDate date) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idEmployeePerformance = idEmployeePerformance;
        this.updatedAttributesList = updatedAttributesList;
        this.data = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Long getIdEmployeePerformance() {
        return idEmployeePerformance;
    }

    public void setIdEmployeePerformance(Long idEmployeePerformance) {
        this.idEmployeePerformance = idEmployeePerformance;
    }

    public List<UpdatedAttributes> getUpdatedAttributesList() {
        return updatedAttributesList;
    }

    public void setUpdatedAttributesList(List<UpdatedAttributes> updatedAttributesList) {
        this.updatedAttributesList = updatedAttributesList;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
