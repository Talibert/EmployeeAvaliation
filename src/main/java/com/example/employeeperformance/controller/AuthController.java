package com.example.employeeperformance.controller;

import com.example.employeeperformance.VOs.AuthVO;
import com.example.employeeperformance.VOs.RegisterVO;
import com.example.employeeperformance.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthVO authVO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authVO.getLogin(), authVO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterVO registerVO){
        if(this.authService.hasUserByLogin(registerVO.getLogin()))
            return ResponseEntity.badRequest().build();

        this.authService.createUser(registerVO);

        return ResponseEntity.ok().build();
    }
}
