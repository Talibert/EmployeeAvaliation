package com.example.employeeperformance.Fixtures;

import com.example.employeeperformance.entities.Attribute;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.types.AttributeType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttributeFixture {

    public static List<Attribute> getAttributeList(List<EmployeePerformance> employeePerformanceList){
        List<Attribute> attributeList = new ArrayList<>();

        for(EmployeePerformance employeePerformance : employeePerformanceList){
            Attribute attributePonctuality = new Attribute(employeePerformance, AttributeType.PONCTUALITY, 5.0);
            Attribute attributeWorkDelivery = new Attribute(employeePerformance, AttributeType.WORK_DELIVERY, 5.0);
            Attribute attributePPEUsage = new Attribute(employeePerformance, AttributeType.PPE_USAGE, 5.0);
            Attribute attributeEvolution = new Attribute(employeePerformance, AttributeType.EVOLUTION, 5.0);
            Attribute attributeCommitment = new Attribute(employeePerformance, AttributeType.COMMITMENT, 5.0);

            attributeList.add(attributePonctuality);
            attributeList.add(attributeWorkDelivery);
            attributeList.add(attributePPEUsage);
            attributeList.add(attributeEvolution);
            attributeList.add(attributeCommitment);
        }

        return attributeList;
    }
}
