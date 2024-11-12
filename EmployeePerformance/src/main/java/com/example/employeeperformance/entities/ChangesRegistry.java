package com.example.employeeperformance.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "changes_registry")
public class ChangesRegistry {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "employee_performance_id", nullable = false)
    private EmployeePerformance employeePerformance;

    @OneToMany(mappedBy = "changesRegistry", fetch = FetchType.LAZY)
    private List<UpdatedAttributes> updatedAttributesList;

    @Column
    private LocalDateTime data;

    public ChangesRegistry() {
    }

    public ChangesRegistry(Employee employee, EmployeePerformance employeePerformance, List<UpdatedAttributes> updatedAttributesList, LocalDateTime data) {
        this.employee = employee;
        this.employeePerformance = employeePerformance;
        this.updatedAttributesList = updatedAttributesList;
        this.data = data;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeePerformance getEmployeePerformance() {
        return employeePerformance;
    }

    public void setEmployeePerformance(EmployeePerformance employeePerformance) {
        this.employeePerformance = employeePerformance;
    }

    public List<UpdatedAttributes> getUpdatedAtributesList() {
        return updatedAttributesList;
    }

    public void addUpdatedAtributesList(UpdatedAttributes updatedAttributes) {
        if(this.updatedAttributesList == null){
            this.updatedAttributesList = new ArrayList<>();
        }
        this.updatedAttributesList.add(updatedAttributes);
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
