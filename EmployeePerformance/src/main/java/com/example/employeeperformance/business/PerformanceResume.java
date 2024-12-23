package com.example.employeeperformance.business;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;

import java.time.Month;
import java.util.Map;

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
}
