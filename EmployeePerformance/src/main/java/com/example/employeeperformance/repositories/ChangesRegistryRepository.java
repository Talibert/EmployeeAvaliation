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

    ChangesRegistry findByEmployeePerformance(EmployeePerformance employeePerformance);

    List<ChangesRegistry> findByData(LocalDate data);

    /**
     * Esse método vai buscar o registro de mudança mais recente de um funcionário... Caso haja mais de um registro por data
     * o desempate será o ID
     * @param employee
     * @return
     */
    @Query("SELECT e FROM ChangesRegistry e " +
            "WHERE e.employee = :employee " +
            "AND e.data = (" +
            "    SELECT MAX(innerE.data) FROM ChangesRegistry innerE " +
            "    WHERE innerE.employee = :employee" +
            ") AND e.id = (" +
            "    SELECT MAX(innerE2.id) FROM ChangesRegistry innerE2 " +
            "    WHERE innerE2.employee = :employee AND innerE2.data = e.data" +
            ")")
    ChangesRegistry findLastChangesRegistryByEmployee(@Param("employee") Employee employee);
}
