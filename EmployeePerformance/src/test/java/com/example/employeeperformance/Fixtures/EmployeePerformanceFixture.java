package com.example.employeeperformance.Fixtures;

import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePerformanceFixture {

    public static List<EmployeePerformance> getEmployeePerformanceListByDates(List<LocalDate> datas, List<Employee> employeeList){
        List<EmployeePerformance> employeePerformanceList = new ArrayList<>();

        for(Employee employee : employeeList){
            for(LocalDate data : datas){
                EmployeePerformance employeePerformance = new EmployeePerformance(data, employee, 5.0, 5.0, 5.0, 5.0, 5.0);
                employeePerformanceList.add(employeePerformance);
            }
        }

        return employeePerformanceList;
    }
}
