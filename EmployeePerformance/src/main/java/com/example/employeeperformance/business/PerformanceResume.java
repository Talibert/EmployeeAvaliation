package com.example.employeeperformance.business;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;

import java.time.Month;
import java.util.Map;

/**
 * Esse objeto vai juntar todas as informações relacionadas as performance do funcionário
 */
public class PerformanceResume {

    /**
     * Performance média do mês/ano solicitado
     */
    private EmployeePerformanceVO performanceAverage;

    /**
     * Ultima performance registrada no mês/ano solicitado
     */
    private EmployeePerformanceVO lastPerformance;

    /**
     * Mapa com as últimas 5 médias de performance mensais anteriores ao mês/ano informado
     */
    private Map<Month, EmployeePerformanceVO> mapPerformanceLastSemester;

    public EmployeePerformanceVO getPerformanceAverage() {
        return performanceAverage;
    }

    public void setPerformanceAverage(EmployeePerformanceVO performanceAverage) {
        this.performanceAverage = performanceAverage;
    }

    public EmployeePerformanceVO getLastPerformance() {
        return lastPerformance;
    }

    public void setLastPerformance(EmployeePerformanceVO lastPerformance) {
        this.lastPerformance = lastPerformance;
    }

    public Map<Month, EmployeePerformanceVO> getMapPerformanceLastSemester() {
        return mapPerformanceLastSemester;
    }

    public void setMapPerformanceLastSemester(Map<Month, EmployeePerformanceVO> mapPerformanceLastSemester) {
        this.mapPerformanceLastSemester = mapPerformanceLastSemester;
    }
}
