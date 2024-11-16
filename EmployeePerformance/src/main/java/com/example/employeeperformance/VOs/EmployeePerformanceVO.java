package com.example.employeeperformance.VOs;

import com.example.employeeperformance.entities.EmployeePerformance;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeePerformanceVO extends AbstractVO{

    private Long id;

    private Long idEmployee;

    private LocalDate date;

    private Double ponctuality;

    private Double workDelivery;

    private Double ppeUsage;

    private Double evolution;

    private Double commitment;

    public EmployeePerformanceVO(){
    }

    public EmployeePerformanceVO(Long idEmployee, LocalDate date) {
        this.idEmployee = idEmployee;
        this.date = date;
    }

    public EmployeePerformanceVO(Long id, Long idEmployee, LocalDate date) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.date = date;
    }

    public EmployeePerformanceVO(Long idEmployee, LocalDate date, Double ponctuality, Double workDelivery, Double ppeUsage, Double evolution, Double commitment) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.date = date;
        this.ponctuality = ponctuality;
        this.workDelivery = workDelivery;
        this.ppeUsage = ppeUsage;
        this.evolution = evolution;
        this.commitment = commitment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPonctuality() {
        return ponctuality;
    }

    public void setPonctuality(Double ponctuality) {
        this.ponctuality = ponctuality;
    }

    public Double getWorkDelivery() {
        return workDelivery;
    }

    public void setWorkDelivery(Double workDelivery) {
        this.workDelivery = workDelivery;
    }

    public Double getPpeUsage() {
        return ppeUsage;
    }

    public void setPpeUsage(Double ppeUsage) {
        this.ppeUsage = ppeUsage;
    }

    public Double getEvolution() {
        return evolution;
    }

    public void setEvolution(Double evolution) {
        this.evolution = evolution;
    }

    public Double getCommitment() {
        return commitment;
    }

    public void setCommitment(Double commitment) {
        this.commitment = commitment;
    }
}
