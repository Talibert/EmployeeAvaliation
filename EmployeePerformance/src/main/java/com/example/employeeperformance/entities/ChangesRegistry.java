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
    private List<UpdatedAtributes> updatedAtributesList;

    @Column
    private LocalDateTime data;

    public ChangesRegistry() {
    }

    public ChangesRegistry(Employee employee, EmployeePerformance employeePerformance, List<UpdatedAtributes> updatedAtributesList, LocalDateTime data) {
        this.employee = employee;
        this.employeePerformance = employeePerformance;
        this.updatedAtributesList = updatedAtributesList;
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

    public List<UpdatedAtributes> getUpdatedAtributesList() {
        return updatedAtributesList;
    }

    public void addUpdatedAtributesList(UpdatedAtributes updatedAtributes) {
        if(this.updatedAtributesList == null){
            this.updatedAtributesList = new ArrayList<>();
        }
        this.updatedAtributesList.add(updatedAtributes);
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
