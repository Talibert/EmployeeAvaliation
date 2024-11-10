package com.example.employeeperformance.entities;

import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Column
    private String nome;

    @NotNull
    @Column
    private String cpf;

    @NotNull
    @Column
    private String observacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private SetorType setorType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private SituationType situationType;

    public Employee(){

    }

    public Employee(String nome, String cpf, String observacao, SetorType setorType, SituationType situationType) {
        this.nome = nome;
        this.cpf = cpf;
        this.observacao = observacao;
        this.setorType = setorType;
        this.situationType = situationType;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public @NotNull String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull String cpf) {
        this.cpf = cpf;
    }

    public @NotNull String getObservacao() {
        return observacao;
    }

    public void setObservacao(@NotNull String observacao) {
        this.observacao = observacao;
    }

    public @NotNull SetorType getSetorType() {
        return setorType;
    }

    public void setSetorType(@NotNull SetorType setorType) {
        this.setorType = setorType;
    }

    public @NotNull SituationType getSituationType() {
        return situationType;
    }

    public void setSituationType(@NotNull SituationType situationType) {
        this.situationType = situationType;
    }
}
