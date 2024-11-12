package com.example.employeeperformance.entities;

import com.example.employeeperformance.types.AttributeType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "updated_atributes")
public class UpdatedAttributes {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "changes_registry_id", nullable = false)
    private ChangesRegistry changesRegistry;

    @Column
    @Enumerated(EnumType.STRING)
    private AttributeType attributeType;

    @Column
    private Integer oldValue;

    @Column
    private Integer newValue;

    public UpdatedAttributes(){
    }

    public UpdatedAttributes(Long id, ChangesRegistry changesRegistry, AttributeType attributeType, Integer oldValue, Integer newValue) {
        Id = id;
        this.changesRegistry = changesRegistry;
        this.attributeType = attributeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public UpdatedAttributes(ChangesRegistry changesRegistry, AttributeType attributeType, Integer oldValue, Integer newValue) {
        this.changesRegistry = changesRegistry;
        this.attributeType = attributeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdatedAttributes that = (UpdatedAttributes) o;
        return Objects.equals(Id, that.Id) && Objects.equals(changesRegistry, that.changesRegistry) && Objects.equals(attributeType, that.attributeType) && Objects.equals(oldValue, that.oldValue) && Objects.equals(newValue, that.newValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, changesRegistry, attributeType, oldValue, newValue);
    }
}
