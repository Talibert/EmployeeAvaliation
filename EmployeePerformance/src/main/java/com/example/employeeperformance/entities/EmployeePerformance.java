package com.example.employeeperformance.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee_performance")
public class EmployeePerformance extends AbstractEntity {

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "employeePerformance")
    private List<Attribute> attributeList;

    public EmployeePerformance() {
    }

    public EmployeePerformance(LocalDate date, Employee employee) {
        this.date = date;
        this.employee = employee;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Attribute> getAttributeList(){
        if(this.attributeList == null)
            this.attributeList = new ArrayList<>();

        return this.attributeList;
    }

    public void addAttributeList(Attribute attribute){
        if(this.attributeList == null)
            this.attributeList = new ArrayList<>();

        this.attributeList.add(attribute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePerformance that = (EmployeePerformance) o;
        return Objects.equals(date, that.date) && Objects.equals(employee, that.employee) && Objects.equals(attributeList, that.attributeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, employee, attributeList);
    }
}
