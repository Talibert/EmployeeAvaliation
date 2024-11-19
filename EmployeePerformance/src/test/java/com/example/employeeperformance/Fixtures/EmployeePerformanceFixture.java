package com.example.employeeperformance.Fixtures;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para facilitar a instanciação de objetos para testes do EmployeePerformance e do EmployeePerformanceVO
 */
public class EmployeePerformanceFixture {

    /**
     * Retorna uma lista de EmployeePerformance com base nas datas e nos Employees recebidos
     * @param datas
     * @param employeeList
     * @return
     */
    public static List<EmployeePerformance> getEmployeePerformanceListByDates(List<LocalDate> datas, List<Employee> employeeList){
        List<EmployeePerformance> employeePerformanceList = new ArrayList<>();

        for(Employee employee : employeeList){
            for(LocalDate data : datas){
                EmployeePerformance employeePerformance = new EmployeePerformance(data, employee);
                employeePerformanceList.add(employeePerformance);
            }
        }

        return employeePerformanceList;
    }

    /**
     * Retorna um EmployeePerformanceVO com atributos fixos
     */
    public static EmployeePerformanceVO getEmployeePerformanceVO(){
        EmployeePerformanceVO employeePerformanceVO = new EmployeePerformanceVO();
        employeePerformanceVO.setPonctuality(3.0);
        employeePerformanceVO.setWorkDelivery(4.0);
        employeePerformanceVO.setPpeUsage(5.0);
        employeePerformanceVO.setEvolution(2.0);
        employeePerformanceVO.setCommitment(1.0);

        return employeePerformanceVO;
    }

    /**
     * Retorna um EmployeePerformanceVO com atributos informados
     */
    public static EmployeePerformanceVO getEmployeePerformanceVO(Double ponctuality, Double workDelivery, Double ppeUsage, Double evolution, Double commitment){
        EmployeePerformanceVO employeePerformanceVO = new EmployeePerformanceVO();
        employeePerformanceVO.setPonctuality(ponctuality);
        employeePerformanceVO.setWorkDelivery(workDelivery);
        employeePerformanceVO.setPpeUsage(ppeUsage);
        employeePerformanceVO.setEvolution(evolution);
        employeePerformanceVO.setCommitment(commitment);

        return employeePerformanceVO;
    }
}
