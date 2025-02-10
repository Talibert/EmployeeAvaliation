package com.example.employeeperformance.repositories;

import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySituationType(SituationType situationType);

    List<Employee> findBySetorType(SetorType setorType);

    List<Employee> findBySetorTypeAndSituationType(SetorType setorType, SituationType situationType);

    Employee findByNome(String nome);
}
