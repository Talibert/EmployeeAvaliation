package com.example.employeeperformance.services;

import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.exceptions.notfound.EmployeePerformanceNotFoundException;
import com.example.employeeperformance.repositories.EmployeePerformanceRepository;
import com.example.employeeperformance.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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
     * Retorna todos os registros atrelados a um funcionário
     * @param id
     * @return
     */
    public List<EmployeePerformance> findByEmployee(Long id){
        Employee employee = employeeService.findById(id);

        return employeePerformanceRepository.findByEmployee(employee);
    }


}
