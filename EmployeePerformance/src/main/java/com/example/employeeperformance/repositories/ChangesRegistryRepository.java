package com.example.employeeperformance.repositories;

import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.entities.UpdatedAttributes;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChangesRegistryRepository extends JpaRepository<ChangesRegistry, Long> {

    List<ChangesRegistry> findByEmployee(Employee employee);

    List<ChangesRegistry> findByEmployeePerformance(EmployeePerformance employeePerformance);

    List<ChangesRegistry> findByData(LocalDate data);

//    @Query("SELECT e FROM ChangesRegistry e " +
//            "WHERE e.date = (" +
//            "    SELECT MAX(innerE.date) FROM ChangesRegistry innerE " +
//            "    WHERE innerE.employee = e.employee" +
//            ") AND e.id = (" +
//            "    SELECT MAX(innerE2.id) FROM ChangesRegistry innerE2 " +
//            "    WHERE innerE2.employee = e.employee AND innerE2.date = e.date" +
//            ")")
//    ChangesRegistry findLastChangesRegistryByEmployee(@Param("employee") Employee employee);
}
