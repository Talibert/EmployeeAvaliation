package com.example.employeeperformance.services;

import com.example.employeeperformance.VOs.UserListResponseVO;
import com.example.employeeperformance.VOs.UserVO;
import com.example.employeeperformance.entities.User;
import com.example.employeeperformance.mappers.UserVoMapper;
import com.example.employeeperformance.repositories.UserRepository;
import com.example.employeeperformance.types.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserVoMapper userVoMapper;

    public UserListResponseVO findAllWithFilter(UserRole userRole){
        UserListResponseVO response = new UserListResponseVO();

        if(userRole != null){
            List<UserVO> listaRetornada = userVoMapper.getListVO(userRepository.findByUserRole(userRole));

            String errorMessage = listaRetornada.isEmpty() ? "Não há usuários cadastrados para a role!" : "";

            response.setUserVOList(listaRetornada);
            response.setErrorMessage(errorMessage);

            return response;
        }

        response.setUserVOList(userVoMapper.getListVO(userRepository.findAll()));
        response.setErrorMessage("");

        return response;
    }
}
