package com.example.employeeperformance.repositories;

import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.types.SituationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeePerformanceRepository extends JpaRepository<EmployeePerformance, Long> {

    List<EmployeePerformance> findByEmployee(Employee employee);
}
