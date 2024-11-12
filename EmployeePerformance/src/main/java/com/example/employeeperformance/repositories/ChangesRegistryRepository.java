package com.example.employeeperformance.repositories;

import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.entities.UpdatedAttributes;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChangesRegistryRepository extends JpaRepository<ChangesRegistry, Long> {

    List<ChangesRegistry> findByEmployee(Employee employee);

    List<ChangesRegistry> findByEmployeePerformance(EmployeePerformance employeePerformance);

    List<ChangesRegistry> findByData(LocalDateTime data);
}
