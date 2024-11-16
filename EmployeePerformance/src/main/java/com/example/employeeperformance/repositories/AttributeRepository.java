package com.example.employeeperformance.repositories;

import com.example.employeeperformance.entities.Attribute;
import com.example.employeeperformance.entities.ChangesRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
