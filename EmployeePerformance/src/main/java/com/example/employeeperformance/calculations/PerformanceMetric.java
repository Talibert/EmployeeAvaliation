package com.example.employeeperformance.calculations;

public class PerformanceMetric {

    Integer count;
    Double value;

    public PerformanceMetric(){
        this.count = 0;
        this.value = 0.0;
    }

    public PerformanceMetric(Integer count, Double value){
        this.count = count;
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Método para incrementar o valor a cada iteração
     * @param value
     */
    public void incrementValue(Double value){
        this.count ++;
        this.value += value;
    }

    /**
     * Método para retornar o valor médio calculado
     * @return
     */
    public Double getAverage(){
        return this.value/this.count;
    }
}
