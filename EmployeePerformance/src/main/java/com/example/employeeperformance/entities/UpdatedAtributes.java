package com.example.employeeperformance.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "updated_atributes")
public class UpdatedAtributes {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "changes_registry_id", nullable = false)
    private ChangesRegistry changesRegistry;

    @Column
    private String updatedAtribute;

    @Column
    private Integer oldValue;

    @Column
    private Integer newValue;


}
