package com.example.employeeperformance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_performance")
public class EmployeePerformance {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    /**
     * A seguir a lista de pontos de avaliação dos funcionários
     */
    @Column
    private Double ponctuality;

    @Column
    private Double workDelivery;

    @Column
    private Double ppeUsage;

    @Column
    private Double evolution;

    @Column
    private Double commitment;

    public EmployeePerformance() {
    }

    public EmployeePerformance(LocalDateTime date, Employee employee, Double ponctuality, Double workDelivery, Double ppeUsage, Double evolution, Double commitment) {
        this.date = date;
        this.employee = employee;
        this.ponctuality = ponctuality;
        this.workDelivery = workDelivery;
        this.ppeUsage = ppeUsage;
        this.evolution = evolution;
        this.commitment = commitment;
    }

    /**
     * Construtor para facilitar os testes
     * @param ponctuality
     * @param workDelivery
     * @param ppeUsage
     * @param evolution
     * @param commitment
     */
    public EmployeePerformance(Double ponctuality, Double workDelivery, Double ppeUsage, Double evolution, Double commitment){
        this.ponctuality = ponctuality;
        this.workDelivery = workDelivery;
        this.ppeUsage = ppeUsage;
        this.evolution = evolution;
        this.commitment = commitment;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
