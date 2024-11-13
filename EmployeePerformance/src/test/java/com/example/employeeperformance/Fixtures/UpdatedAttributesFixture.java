package com.example.employeeperformance.Fixtures;

import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.entities.UpdatedAttributes;
import com.example.employeeperformance.types.AttributeType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpdatedAttributesFixture {

    public static List<UpdatedAttributes> getUpdatedAttributesList(ChangesRegistry changesRegistrySaved){
        List<UpdatedAttributes> updatedAttributesList = new ArrayList<>();

        UpdatedAttributes updatedAttributes1 = new UpdatedAttributes(changesRegistrySaved, AttributeType.PONCTUALITY, 5, 4);
        UpdatedAttributes updatedAttributes2 = new UpdatedAttributes(changesRegistrySaved, AttributeType.WORK_DELIVERY, 4, 3);
        UpdatedAttributes updatedAttributes3 = new UpdatedAttributes(changesRegistrySaved, AttributeType.PPE_USAGE, 3, 5);
        UpdatedAttributes updatedAttributes4 = new UpdatedAttributes(changesRegistrySaved, AttributeType.EVOLUTION, 5, 3);
        UpdatedAttributes updatedAttributes5 = new UpdatedAttributes(changesRegistrySaved, AttributeType.COMMITMENT, 2, 5);

        updatedAttributesList.add(updatedAttributes1);
        updatedAttributesList.add(updatedAttributes2);
        updatedAttributesList.add(updatedAttributes3);
        updatedAttributesList.add(updatedAttributes4);
        updatedAttributesList.add(updatedAttributes5);

        return updatedAttributesList;
    }
}