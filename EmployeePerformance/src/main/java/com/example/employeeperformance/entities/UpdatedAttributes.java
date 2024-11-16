package com.example.employeeperformance.entities;

import com.example.employeeperformance.types.AttributeType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "updated_atributes")
public class UpdatedAttributes extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "changes_registry_id")
    private ChangesRegistry changesRegistry;

    @Column
    @Enumerated(EnumType.STRING)
    private AttributeType attributeType;

    @Column
    private Double oldValue;

    @Column
    private Double newValue;

    public UpdatedAttributes(){
    }

    public UpdatedAttributes(Long id, ChangesRegistry changesRegistry, AttributeType attributeType, Double oldValue, Double newValue) {
        this.id = id;
        this.changesRegistry = changesRegistry;
        this.attributeType = attributeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public UpdatedAttributes(ChangesRegistry changesRegistry, AttributeType attributeType, Double oldValue, Double newValue) {
        this.changesRegistry = changesRegistry;
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

    public ChangesRegistry getChangesRegistry() {
        return changesRegistry;
    }

    public void setChangesRegistry(ChangesRegistry changesRegistry) {
        this.changesRegistry = changesRegistry;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdatedAttributes that = (UpdatedAttributes) o;
        return Objects.equals(id, that.id) && Objects.equals(changesRegistry, that.changesRegistry) && Objects.equals(attributeType, that.attributeType) && Objects.equals(oldValue, that.oldValue) && Objects.equals(newValue, that.newValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, changesRegistry, attributeType, oldValue, newValue);
    }
}
