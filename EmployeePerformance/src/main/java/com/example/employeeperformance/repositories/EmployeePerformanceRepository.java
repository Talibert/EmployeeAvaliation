package com.example.employeeperformance.repositories;

import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeePerformanceRepository extends JpaRepository<EmployeePerformance, Long> {

    List<EmployeePerformance> findByEmployee(Employee employee);

    @Query("SELECT p FROM EmployeePerformance p " +
            "WHERE MONTH(p.date) = :mes " +
            "AND YEAR(p.date) = :ano")
    List<EmployeePerformance> findByMesEAno(@Param("mes") int mes, @Param("ano") int ano);

    @Query("SELECT p FROM EmployeePerformance p " +
            "WHERE MONTH(p.date) = :mes " +
            "AND YEAR(p.date) = :ano " +
            "AND p.employee = :employee")
    List<EmployeePerformance> findByMesAnoEEmployee(@Param("mes") int mes, @Param("ano") int ano, @Param("employee") Employee employee);

    @Query("SELECT p FROM EmployeePerformance p " +
            "WHERE MONTH(p.date) = :mes " +
            "AND YEAR(p.date) = :ano " +
            "AND p.employee = :employee")
    EmployeePerformance findByMesAnoEEmployeeLastRegistry(@Param("mes") int mes, @Param("ano") int ano, @Param("employee") Employee employee);
}
