package com.example.employeeperformance.mappers;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.services.EmployeeService;
import com.example.employeeperformance.types.SituationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EmployeeVoMapper extends SuperMapper{

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
     * Metodo para converter o VO em uma entidade para fazer o update no banco
     * @param employeeVO
     * @return
     */
    public Employee getEntityToUpdate(EmployeeVO employeeVO, Employee employee){
        if (!Objects.equals(employeeVO.getNome(), employee.getNome())) {
            employee.setNome(employeeVO.getNome());
        }
        if (!Objects.equals(employeeVO.getCpf(), employee.getCpf())) {
            employee.setCpf(employeeVO.getCpf());
        }
        if (!Objects.equals(employeeVO.getObservacao(), employee.getObservacao())) {
            employee.setObservacao(employeeVO.getObservacao());
        }
        if (!Objects.equals(employeeVO.getSetorType(), employee.getSetorType())) {
            employee.setSetorType(employeeVO.getSetorType());
        }
        if (!Objects.equals(employeeVO.getSituationType(), employee.getSituationType())) {
            employee.setSituationType(employeeVO.getSituationType());
        }

        return employee;
    }

    /**
     * Metodo para converter o VO em uma entidade que será nova no banco
     * @param employeeVO
     * @return
     */
    public Employee getEntityToCreate(EmployeeVO employeeVO){
        return new Employee(
                employeeVO.getNome(),
                employeeVO.getCpf(),
                employeeVO.getObservacao(),
                employeeVO.getSetorType(),
                SituationType.ATIVO
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

    /**
     * Metodo para converter uma lista de entidades em uma lista de VOs
     * @param employeeVOList
     * @return
     */
    public List<Employee> getListEntity(List<EmployeeVO> employeeVOList){
        return employeeVOList.stream().map(this::getEntity).collect(Collectors.toList());
    }
}
