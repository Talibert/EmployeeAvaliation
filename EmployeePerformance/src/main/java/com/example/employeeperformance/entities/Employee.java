package com.example.employeeperformance.entities;

import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String nome;

    @Column
    private String cpf;

    @Column
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column
    private SetorType setorType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(Id, employee.Id) && Objects.equals(nome, employee.nome) && Objects.equals(cpf, employee.cpf) && Objects.equals(observacao, employee.observacao) && setorType == employee.setorType && situationType == employee.situationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nome, cpf, observacao, setorType, situationType);
    }
}
