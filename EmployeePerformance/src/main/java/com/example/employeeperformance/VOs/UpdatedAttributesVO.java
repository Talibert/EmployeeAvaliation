package com.example.employeeperformance.VOs;

import com.example.employeeperformance.types.AttributeType;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;

public class UpdatedAttributesVO {

    private Long id;

    private Long idChangesRegistry;

    private AttributeType attributeType;

    private Integer oldValue;

    private Integer newValue;

    public UpdatedAttributesVO(){
    }

    public UpdatedAttributesVO(Long id, Long idChangesRegistry, AttributeType attributeType, Integer oldValue, Integer newValue) {
        this.id = id;
        this.idChangesRegistry = idChangesRegistry;
        this.attributeType = attributeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public UpdatedAttributesVO(Long idChangesRegistry, AttributeType attributeType, Integer oldValue, Integer newValue) {
        this.idChangesRegistry = idChangesRegistry;
        this.attributeType = attributeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public UpdatedAttributesVO(AttributeType attributeType, Integer oldValue, Integer newValue) {
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

    public Integer getOldValue() {
        return oldValue;
    }

    public void setOldValue(Integer oldValue) {
        this.oldValue = oldValue;
    }

    public Integer getNewValue() {
        return newValue;
    }

    public void setNewValue(Integer newValue) {
        this.newValue = newValue;
    }
}
