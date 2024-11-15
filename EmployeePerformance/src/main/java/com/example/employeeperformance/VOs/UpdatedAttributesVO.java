package com.example.employeeperformance.VOs;

import com.example.employeeperformance.types.AttributeType;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import jdk.incubator.vector.DoubleVector;

public class UpdatedAttributesVO {

    private Long id;

    private Long idChangesRegistry;

    private AttributeType attributeType;

    private Double oldValue;

    private Double newValue;

    public UpdatedAttributesVO(){
    }

    public UpdatedAttributesVO(Long id, Long idChangesRegistry, AttributeType attributeType, Double oldValue, Double newValue) {
        this.id = id;
        this.idChangesRegistry = idChangesRegistry;
        this.attributeType = attributeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public UpdatedAttributesVO(Long idChangesRegistry, AttributeType attributeType, Double oldValue, Double newValue) {
        this.idChangesRegistry = idChangesRegistry;
        this.attributeType = attributeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public UpdatedAttributesVO(AttributeType attributeType, Double oldValue, Double newValue) {
        this.attributeType = attributeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdChangesRegistry() {
        return idChangesRegistry;
    }

    public void setIdChangesRegistry(Long idChangesRegistry) {
        this.idChangesRegistry = idChangesRegistry;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public Double getOldValue() {
        return oldValue;
    }

    public void setOldValue(Double oldValue) {
        this.oldValue = oldValue;
    }

    public Double getNewValue() {
        return newValue;
    }

    public void setNewValue(Double newValue) {
        this.newValue = newValue;
    }
}
