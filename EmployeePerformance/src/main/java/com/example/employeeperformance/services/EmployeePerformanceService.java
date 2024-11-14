package com.example.employeeperformance.services;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.calculations.PerformanceMetric;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.exceptions.notfound.EmployeePerformanceNotFoundException;
import com.example.employeeperformance.repositories.EmployeePerformanceRepository;
import com.example.employeeperformance.types.AttributeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class EmployeePerformanceService {

    @Autowired
    private EmployeePerformanceRepository employeePerformanceRepository;

    @Autowired
    private EmployeeService employeeService;

    public EmployeePerformance findById(Long id){
        Optional<EmployeePerformance> employeePerformance = employeePerformanceRepository.findById(id);

        if(employeePerformance.isPresent())
            return employeePerformance.get();

        throw new EmployeePerformanceNotFoundException("Registro de performance não encontrado");
    }

    /**
     * Retorna todos os registros atrelados a performance de um funcionário
     * @param id
     * @return
     */
    public List<EmployeePerformance> findByEmployee(Long id){
        Employee employee = employeeService.findById(id);

        return employeePerformanceRepository.findByEmployee(employee);
    }

    /**
     * Retorna todas as performances de um mês em um ano específico
     * @param month
     * @param year
     * @return
     */
    public List<EmployeePerformance> findByYearAndMonth(Month month, Year year){
        return employeePerformanceRepository.findByMesEAno(month.get(ChronoField.MONTH_OF_YEAR), year.get(ChronoField.YEAR));
    }

    /**
     * Retorna todas as performances de um mês e ano específico, para um funcionário específico
     * @param month
     * @param year
     * @return
     */
    public List<EmployeePerformance> findByYearMonthAndEmployee(Month month, Year year, Employee employee){
        return employeePerformanceRepository.findByMesAnoEEmployee(month.get(ChronoField.MONTH_OF_YEAR), year.get(ChronoField.YEAR), employee);
    }

    /**
     * Retorna a média de performance do funcionário solicitado no mês e ano informado
     * @param month
     * @param year
     * @param id
     * @return
     */
    public EmployeePerformanceVO getEmployeePerformanceAverageByYearAndMonth(Month month, Year year, Long id){
        Employee employee = employeeService.findById(id);

        List<EmployeePerformance> employeePerformanceList = findByYearMonthAndEmployee(month, year, employee);

        Map<AttributeType, PerformanceMetric> performanceMap = popularPerformanceMap(employeePerformanceList);

        return getEmployeePerformanceAverage(performanceMap, id, month, year);
    }

    /**
     * Popula o mapa de performance do funcionário
     * @param employeePerformanceList
     * @return
     */
    public Map<AttributeType, PerformanceMetric> popularPerformanceMap(List<EmployeePerformance> employeePerformanceList){
        PerformanceMetric performacePonctuality = new PerformanceMetric();
        PerformanceMetric performaceWorkDelivery = new PerformanceMetric();
        PerformanceMetric performacePPEUsage = new PerformanceMetric();
        PerformanceMetric performaceEvolution = new PerformanceMetric();
        PerformanceMetric performaceCommitment = new PerformanceMetric();

        for(EmployeePerformance employeePerformance : employeePerformanceList){
            performacePonctuality.incrementValue(employeePerformance.getPonctuality());
            performaceWorkDelivery.incrementValue(employeePerformance.getWorkDelivery());
            performacePPEUsage.incrementValue(employeePerformance.getPpeUsage());
            performaceEvolution.incrementValue(employeePerformance.getEvolution());
            performaceCommitment.incrementValue(employeePerformance.getCommitment());
        }

        Map<AttributeType, PerformanceMetric> map = new HashMap<>();
        map.put(AttributeType.PONCTUALITY, performacePonctuality);
        map.put(AttributeType.WORK_DELIVERY, performaceWorkDelivery);
        map.put(AttributeType.PPE_USAGE, performacePPEUsage);
        map.put(AttributeType.EVOLUTION, performaceEvolution);
        map.put(AttributeType.COMMITMENT, performaceCommitment);

        return map;
    }

    /**
     * Cria um objeto VO do funcionário que representa a média dos registros no mês e ano informados
     * @param performanceMap
     * @param id
     * @param month
     * @param year
     * @return
     */
    public EmployeePerformanceVO getEmployeePerformanceAverage(Map<AttributeType, PerformanceMetric> performanceMap, Long id, Month month, Year year){
        LocalDate date = LocalDate.of(
                year.getValue(),
                month.getValue(),
                month.maxLength()
        );

        return new EmployeePerformanceVO(
                id, date,
                performanceMap.get(AttributeType.PONCTUALITY).getAverage(),
                performanceMap.get(AttributeType.WORK_DELIVERY).getAverage(),
                performanceMap.get(AttributeType.PPE_USAGE).getAverage(),
                performanceMap.get(AttributeType.EVOLUTION).getAverage(),
                performanceMap.get(AttributeType.COMMITMENT).getAverage()
        );
    }
}
