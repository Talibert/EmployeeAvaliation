package com.example.employeeperformance.mappers;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeVoMapper {

    /**
     * Metodo para converter a entidade no VO
     * @param employee
     * @return
     */
    public EmployeeVO getVO(Employee employee){
        return new EmployeeVO(
                employee.getId(),
                employee.getNome(),
                employee.getCpf(),
                employee.getObservacao(),
                employee.getSetorType(),
                employee.getSituationType()
        );
    }

    /**
     * Metodo para converter o VO em uma entidade
     * @param employeeVO
     * @return
     */
    public Employee getEntity(EmployeeVO employeeVO){
        return new Employee(
                employeeVO.getNome(),
                employeeVO.getCpf(),
                employeeVO.getObservacao(),
                employeeVO.getSetorType(),
                employeeVO.getSituationType()
        );
    }

    /**
     * Metodo para converter uma lista de entidades em uma lista de VOs
     * @param employeeList
     * @return
     */
    public List<EmployeeVO> getListVO(List<Employee> employeeList){
        return employeeList.stream().map(this::getVO).collect(Collectors.toList());
    }
}
