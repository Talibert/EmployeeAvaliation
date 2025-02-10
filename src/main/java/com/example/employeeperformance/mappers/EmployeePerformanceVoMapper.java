package com.example.employeeperformance.mappers;

import com.example.employeeperformance.VOs.EmployeePerformanceVO;
import com.example.employeeperformance.entities.Attribute;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeePerformanceVoMapper extends SuperMapper{

    /**
     * Metodo para converter a entidade no VO
     * @param employeePerformance
     * @return
     */
    public EmployeePerformanceVO getVO(EmployeePerformance employeePerformance){
        EmployeePerformanceVO employeePerformanceVO = new EmployeePerformanceVO(
                employeePerformance.getId(),
                employeePerformance.getEmployee().getId(),
                employeePerformance.getDate());

        populaAtributos(employeePerformance, employeePerformanceVO);

        return employeePerformanceVO;
    }

    /**
     * Metodo para converter o VO em uma entidade
     * @param employeePerformanceVO
     * @return
     */
    public EmployeePerformance getEntity(EmployeePerformanceVO employeePerformanceVO){
        return new EmployeePerformance(
                employeePerformanceVO.getDate(),
                getEmployee(employeePerformanceVO.getIdEmployee())
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

    /**
     * Metodo para converter uma lista de entidades em uma lista de VOs
     * @param employeePerformanceVOList
     * @return
     */
    public List<EmployeePerformance> getListEntity(List<EmployeePerformanceVO> employeePerformanceVOList){
        return employeePerformanceVOList.stream().map(this::getEntity).collect(Collectors.toList());
    }

    public void populaAtributos(EmployeePerformance employeePerformance, EmployeePerformanceVO employeePerformanceVO){
        List<Attribute> attributeList = employeePerformance.getAttributeList();

        for(Attribute attribute : attributeList){
            switch (attribute.getAttributeType()){
                case PONCTUALITY:
                    employeePerformanceVO.setPonctuality(attribute.getValue());
                    break;
                case WORK_DELIVERY:
                    employeePerformanceVO.setWorkDelivery(attribute.getValue());
                    break;
                case PPE_USAGE:
                    employeePerformanceVO.setPpeUsage(attribute.getValue());
                    break;
                case EVOLUTION:
                    employeePerformanceVO.setEvolution(attribute.getValue());
                    break;
                case COMMITMENT:
                    employeePerformanceVO.setCommitment(attribute.getValue());
                    break;
                default:
                    break;
            }
        }
    }
}
