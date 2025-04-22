package com.example.employeeperformance.services.security;

import com.example.employeeperformance.VOs.RegisterVO;
import com.example.employeeperformance.entities.User;
import com.example.employeeperformance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service que serve para gerenciar a lógica de autenticação dos usuários
 */
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public void createUser(RegisterVO registerVO){
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerVO.getPassword());
        User user = new User(registerVO.getLogin(), encryptedPassword, registerVO.getUserRole());

        this.userRepository.save(user);
    }
}
