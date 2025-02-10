package com.example.employeeperformance.services;

import com.example.employeeperformance.Fixtures.EmployeePerformanceFixture;
import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.entities.Attribute;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.types.AttributeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TesteAttributesService {

    @Spy
    private AttributesService attributesService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void montaListaDeAtributos() {
        EmployeePerformance employeePerformance = new EmployeePerformance();
        EmployeePerformanceVO employeePerformanceVO = EmployeePerformanceFixture.getEmployeePerformanceVO();
        List<Attribute> attributeList = attributesService.montaListaDeAtributos(employeePerformance, employeePerformanceVO);

        Assertions.assertEquals(5, attributeList.size());

        attributeList.forEach(a -> Assertions.assertEquals(employeePerformance, a.getEmployeePerformance()));
    }
}