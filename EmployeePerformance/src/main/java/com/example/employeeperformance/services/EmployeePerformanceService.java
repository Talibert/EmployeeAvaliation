package com.example.employeeperformance.services;

import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.exceptions.notfound.EmployeePerformanceNotFoundException;
import com.example.employeeperformance.repositories.EmployeePerformanceRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public EmployeePerformance getEmployeePerformanceAverageByYearAndMonth(Month month, Year year, Long id){
        Employee employee = employeeService.findById(id);

        List<EmployeePerformance> employeePerformanceList = findByYearMonthAndEmployee(month, year, employee);

        Map<String, Pair<Integer, Double>> mapaPerformance = populaMapaPerformance(employeePerformanceList);


    }

    public Map<String, Pair<Integer, Double>> populaMapaPerformance(List<EmployeePerformance> employeePerformanceList){
        Integer iteracao = employeePerformanceList.size();

        Pair<Integer, Double> ponctualityPair = Pair.of(iteracao, 0.0);
        Pair<Integer, Double> workDeliveryPair = Pair.of(iteracao, 0.0);
        Pair<Integer, Double> ppeUsagePair = Pair.of(iteracao, 0.0);
        Pair<Integer, Double> evolutionPair = Pair.of(iteracao, 0.0);
        Pair<Integer, Double> commitmentPair = Pair.of(iteracao, 0.0);

        for(EmployeePerformance employeePerformance : employeePerformanceList){
            Double ponctuality = ponctualityPair.getRight() + employeePerformance.getPonctuality();
            ponctualityPair.setValue(ponctuality);

            Double workDelivery = workDeliveryPair.getRight() + employeePerformance.getWorkDelivery();
            workDeliveryPair.setValue(workDelivery);

            Double ppeUsage = ppeUsagePair.getRight() + employeePerformance.getPpeUsage();
            ppeUsagePair.setValue(ppeUsage);

            Double evolution = evolutionPair.getRight() + employeePerformance.getEvolution();
            evolutionPair.setValue(evolution);

            Double commitment = commitmentPair.getRight() + employeePerformance.getCommitment();
            commitmentPair.setValue(commitment);
        }

        Map<String, Pair<Integer, Double>> map = new HashMap<>();
        map.put("ponctuality", ponctualityPair);
        map.put("workDelivery", workDeliveryPair);
        map.put("ppeUsage", ppeUsagePair);
        map.put("evolution", evolutionPair);
        map.put("commitment", commitmentPair);

        return map;
    }
}
