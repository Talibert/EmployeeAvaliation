package com.example.employeeperformance.entities;

import com.example.employeeperformance.types.AttributeType;
import jakarta.persistence.*;

@Entity
@Table(name = "attribute")
public class Attribute extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "employee_performance_id")
    private EmployeePerformance employeePerformance;

    @Column
    @Enumerated(EnumType.STRING)
    private AttributeType attributeType;

    @Column(name = "attribute_value")
    private Double value;

    public Attribute(){

    }

    public Attribute(EmployeePerformance employeePerformance, AttributeType attributeType, Double value) {
        this.employeePerformance = employeePerformance;
        this.attributeType = attributeType;
        this.value = value;
    }

    public EmployeePerformance getEmployeePerformance() {
        return employeePerformance;
    }

    public void setEmployeePerformance(EmployeePerformance employeePerformance) {
        this.employeePerformance = employeePerformance;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
