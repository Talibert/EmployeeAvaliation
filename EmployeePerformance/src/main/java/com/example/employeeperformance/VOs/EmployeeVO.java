package com.example.employeeperformance.VOs;

import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;

import java.time.LocalDate;

public class EmployeeVO extends AbstractVO{

    private Long id;

    private String nome;

    private String cpf;

    private String observacao;

    private SetorType setorType;

    private SituationType situationType;

    public EmployeeVO(){
    }

    public EmployeeVO(String nome, String cpf, String observacao, SetorType setorType, SituationType situationType) {
        this.nome = nome;
        this.cpf = cpf;
        this.observacao = observacao;
        this.setorType = setorType;
        this.situationType = situationType;
    }

    public EmployeeVO(Long id, String nome, String cpf, String observacao, SetorType setorType, SituationType situationType) {
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

    public void setCpf(String cpf) {
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
}
