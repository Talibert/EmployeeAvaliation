package com.example.employeeperformance.controller;

import com.example.employeeperformance.VOs.ErrorResponseVO;
import com.example.employeeperformance.VOs.UserListResponseVO;
import com.example.employeeperformance.services.UserService;
import com.example.employeeperformance.types.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint para retornar a lista de usuários cadastrados no banco
     *
     * E metodo pode ou não receber um filter de role.
     */
    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(required = false) UserRole userRole){
        UserListResponseVO userListResponseVO = userService.findAllWithFilter(userRole);

        if(!userListResponseVO.getErrorMessage().isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO(userListResponseVO.getErrorMessage(), "USER_FETCH_FAILED"));

        return ResponseEntity.status(HttpStatus.OK).body(userListResponseVO.getUserVOList());
    }
}
