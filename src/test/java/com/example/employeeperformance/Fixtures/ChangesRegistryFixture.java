package com.example.employeeperformance.Fixtures;

import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChangesRegistryFixture {

    public static List<ChangesRegistry> getChangesRegistryListByEmployeePerformance(List<EmployeePerformance> employeePerformanceList){
        List<ChangesRegistry> changesRegistryList = new ArrayList<>();

        employeePerformanceList.forEach(e -> changesRegistryList.add(
                new ChangesRegistry(e.getEmployee(), e, null, e.getDate())
        ));

        return changesRegistryList;
    }
}
