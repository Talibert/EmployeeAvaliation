package com.example.employeeproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_performance")
public class EmployeePerformance {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    /**
     * A seguir a lista de pontos de avaliação dos funcionários
     */
    @Column
    private Integer ponctuality;

    @Column
    private Integer workDelivery;

    @Column
    private Integer ppeUsage;

    @Column
    private Integer evolution;

    @Column
    private Integer commitment;

    public EmployeePerformance() {
    }

    public EmployeePerformance(LocalDateTime date, Employee employee, Integer ponctuality, Integer workDelivery, Integer ppeUsage, Integer evolution, Integer commitment) {
        this.date = date;
        this.employee = employee;
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

    public @NotNull LocalDateTime getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDateTime date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getPonctuality() {
        return ponctuality;
    }

    public void setPonctuality(Integer ponctuality) {
        this.ponctuality = ponctuality;
    }

    public Integer getWorkDelivery() {
        return workDelivery;
    }

    public void setWorkDelivery(Integer workDelivery) {
        this.workDelivery = workDelivery;
    }

    public Integer getPpeUsage() {
        return ppeUsage;
    }

    public void setPpeUsage(Integer ppeUsage) {
        this.ppeUsage = ppeUsage;
    }

    public Integer getEvolution() {
        return evolution;
    }

    public void setEvolution(Integer evolution) {
        this.evolution = evolution;
    }

    public Integer getCommitment() {
        return commitment;
    }

    public void setCommitment(Integer commitment) {
        this.commitment = commitment;
    }
}
