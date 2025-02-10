package com.example.employeeperformance.Fixtures;

import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFixture {

    public static List<Employee> getEmployeeList(){
        Employee employee1 = new Employee(1L,"Yasuo", "111.111.111-11", "Observação", SetorType.OFFICE, SituationType.ATIVO);
        Employee employee2 = new Employee(2L,"Kassadin", "222.222.222-22", "Observação", SetorType.STOCK, SituationType.INATIVO);
        Employee employee3 = new Employee(3L,"Caitlyn", "333.333.333-33", "Observação", SetorType.OTHERS, SituationType.ATIVO);
        Employee employee4 = new Employee(4L,"Vi", "444.444.444-44", "Observação", SetorType.OFFICE, SituationType.INATIVO);
        Employee employee5 = new Employee(5L,"Ezreal", "555.555.555-55", "Observação", SetorType.OFFICE, SituationType.ATIVO);
        Employee employee6 = new Employee(6L,"Aphelios", "666.666.666-66", "Observação", SetorType.OTHERS, SituationType.INATIVO);
        Employee employee7 = new Employee(7L,"Leona", "777.777.777-77", "Observação", SetorType.STOCK, SituationType.ATIVO);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);
        employeeList.add(employee5);
        employeeList.add(employee6);
        employeeList.add(employee7);

        return employeeList;
    }
}
