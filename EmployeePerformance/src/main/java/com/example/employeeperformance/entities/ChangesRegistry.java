package com.example.employeeperformance.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "changes_registry")
public class ChangesRegistry extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "employee_performance_id")
    private EmployeePerformance employeePerformance;

    @OneToMany(mappedBy = "changesRegistry")
    private List<UpdatedAttributes> updatedAttributesList;

    @Column
    private LocalDate data;

    public ChangesRegistry() {
    }

    public ChangesRegistry(Employee employee, EmployeePerformance employeePerformance, List<UpdatedAttributes> updatedAttributesList, LocalDate data) {
        this.employee = employee;
        this.employeePerformance = employeePerformance;
        this.updatedAttributesList = updatedAttributesList;
        this.data = data;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangesRegistry that = (ChangesRegistry) o;
        return Objects.equals(this.id, that.id) && Objects.equals(employee, that.employee) && Objects.equals(employeePerformance, that.employeePerformance) && Objects.equals(updatedAttributesList, that.updatedAttributesList) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, employee, employeePerformance, updatedAttributesList, data);
    }
}
