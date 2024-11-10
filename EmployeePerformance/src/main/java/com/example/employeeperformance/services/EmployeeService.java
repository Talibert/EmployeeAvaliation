package com.example.employeeperformance.services;

import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.exceptions.notfound.EmployeeNotFoundException;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.types.SituationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Método de busca de funcionários pelo id
     * @param id
     * @return
     */
    public Employee findById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent())
            return employee.get();

        throw new EmployeeNotFoundException("O funcionário não foi encontrado");
    }

    /**
     * Método que irá retornar uma lista de funcionários ativos
     * @return
     */
    public List<Employee> findAllActive(){
        return employeeRepository.findBySituationType(SituationType.ATIVO);
    }
}
