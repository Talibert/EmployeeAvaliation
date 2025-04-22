package com.example.employeeperformance.mappers;

import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.VOs.UserVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.User;
import com.example.employeeperformance.types.SituationType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserVoMapper extends SuperMapper{

    /**
     * Metodo para converter a entidade no VO
     * @param user
     * @return
     */
    public UserVO getVO(User user){
        return new UserVO(
                user.getLogin(),
                user.getUserRole()
        );
    }

    /**
     * Metodo para converter uma lista de entidades em uma lista de VOs
     * @param userList
     * @return
     */
    public List<UserVO> getListVO(List<User> userList){
        return userList.stream().map(this::getVO).collect(Collectors.toList());
    }
}
