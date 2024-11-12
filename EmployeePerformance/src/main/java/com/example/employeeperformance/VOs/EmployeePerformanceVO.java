package com.example.employeeperformance.VOs;

import com.example.employeeperformance.entities.EmployeePerformance;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class EmployeePerformanceVO {

    private Long idEmployee;

    private LocalDateTime date;

    private Double ponctuality;

    private Double workDelivery;

    private Double ppeUsage;

    private Double evolution;

    private Double commitment;

    public EmployeePerformanceVO(){
    }

    public EmployeePerformanceVO(Long idEmployee, LocalDateTime date, Double ponctuality, Double workDelivery, Double ppeUsage, Double evolution, Double commitment) {
        this.idEmployee = idEmployee;
        this.date = date;
        this.ponctuality = ponctuality;
        this.workDelivery = workDelivery;
        this.ppeUsage = ppeUsage;
        this.evolution = evolution;
        this.commitment = commitment;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
