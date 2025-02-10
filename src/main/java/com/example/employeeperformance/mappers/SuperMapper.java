package com.example.employeeperformance.mappers;

import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.entities.UpdatedAttributes;
import com.example.employeeperformance.exceptions.notfound.ChangesRegistryNotFoundException;
import com.example.employeeperformance.exceptions.notfound.EmployeeNotFoundException;
import com.example.employeeperformance.exceptions.notfound.EmployeePerformanceNotFoundException;
import com.example.employeeperformance.exceptions.notfound.UpdatedAttributesNotFoundException;
import com.example.employeeperformance.repositories.ChangesRegistryRepository;
import com.example.employeeperformance.repositories.EmployeePerformanceRepository;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.repositories.UpdatedAttributesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SuperMapper {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeePerformanceRepository employeePerformanceRepository;

    @Autowired
    private ChangesRegistryRepository changesRegistryRepository;

    @Autowired
    private UpdatedAttributesRepository updatedAttributesRepository;

    protected Employee getEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent())
            return employee.get();

        throw new EmployeeNotFoundException();
    }

    protected EmployeePerformance getEmployeePerformance(Long id){
        Optional<EmployeePerformance> employeePerformance = employeePerformanceRepository.findById(id);
        if(employeePerformance.isPresent())
            return employeePerformance.get();

        throw new EmployeePerformanceNotFoundException();
    }

    protected ChangesRegistry getChangesRegistry(Long id){
        Optional<ChangesRegistry> changesRegistry = changesRegistryRepository.findById(id);
        if(changesRegistry.isPresent())
            return changesRegistry.get();

        throw new ChangesRegistryNotFoundException();
    }

    protected UpdatedAttributes getUpdatedAttributes(Long id){
        Optional<UpdatedAttributes> updatedAttributes = updatedAttributesRepository.findById(id);
        if(updatedAttributes.isPresent())
            return updatedAttributes.get();

        throw new UpdatedAttributesNotFoundException();
    }
}
