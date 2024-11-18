package com.example.employeeperformance.repositories;

import com.example.employeeperformance.entities.Attribute;
import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.types.AttributeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    public List<Attribute> findByAttributeType(AttributeType attributeType);

    public List<Attribute> findByEmployeePerformance(EmployeePerformance employeePerformance);

    public Attribute findByEmployeePerformanceAndAttributeType(EmployeePerformance employeePerformance, AttributeType attributeType);
}
