package com.example.employeeperformance.repositories;

import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangesRegistryRepository extends JpaRepository<ChangesRegistry, Long> {
}
