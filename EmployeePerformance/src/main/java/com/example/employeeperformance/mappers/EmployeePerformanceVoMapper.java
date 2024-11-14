package com.example.employeeperformance.mappers;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeePerformanceVoMapper {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Metodo para converter a entidade no VO
     * @param employeePerformance
     * @return
     */
    public EmployeePerformanceVO getVO(EmployeePerformance employeePerformance){
        return new EmployeePerformanceVO(
                employeePerformance.getId(),
                employeePerformance.getEmployee().getId(),
                employeePerformance.getDate(),
                employeePerformance.getPonctuality(),
                employeePerformance.getWorkDelivery(),
                employeePerformance.getPpeUsage(),
                employeePerformance.getEvolution(),
                employeePerformance.getCommitment());
    }

    /**
     * Metodo para converter o VO em uma entidade
     * @param employeePerformanceVO
     * @return
     */
    public EmployeePerformance getEntity(EmployeePerformanceVO employeePerformanceVO){
        return new EmployeePerformance(
                employeePerformanceVO.getDate(),
                getEmployee(employeePerformanceVO.getIdEmployee()),
                employeePerformanceVO.getPonctuality(),
                employeePerformanceVO.getWorkDelivery(),
                employeePerformanceVO.getPpeUsage(),
                employeePerformanceVO.getEvolution(),
                employeePerformanceVO.getCommitment()
        );
    }

    /**
     * Metodo para converter uma lista de entidades em uma lista de VOs
     * @param employeePerformanceList
     * @return
     */
    public List<EmployeePerformanceVO> getListVO(List<EmployeePerformance> employeePerformanceList){
        return employeePerformanceList.stream().map(this::getVO).collect(Collectors.toList());
    }

    public Employee getEmployee(Long id){
        return employeeService.findById(id);
    }
}
