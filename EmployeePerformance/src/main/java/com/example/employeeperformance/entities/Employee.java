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
    private Long id;

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

    public Employee(Long id, String nome, String cpf, String observacao, SetorType setorType, SituationType situationType) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.observacao = observacao;
        this.setorType = setorType;
        this.situationType = situationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull String cpf) {
        this.cpf = cpf;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public SetorType getSetorType() {
        return setorType;
    }

    public void setSetorType(SetorType setorType) {
        this.setorType = setorType;
    }

    public SituationType getSituationType() {
        return situationType;
    }

    public void setSituationType(SituationType situationType) {
        this.situationType = situationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(nome, employee.nome) && Objects.equals(cpf, employee.cpf) && Objects.equals(observacao, employee.observacao) && setorType == employee.setorType && situationType == employee.situationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, observacao, setorType, situationType);
    }
}
