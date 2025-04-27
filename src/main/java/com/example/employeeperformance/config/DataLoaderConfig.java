package com.example.employeeperformance.config;

import com.example.employeeperformance.entities.User;
import com.example.employeeperformance.repositories.UserRepository;
import com.example.employeeperformance.types.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * O objetivo dessa classe de config é popular o banco, caso ele esteja vazio, quando a aplicação subir
 */
@Configuration
public class DataLoaderConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Vamos criar, por padrão, um usuário Admin
     */
    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                String login = "GuilhermeTaliberti";
                String password = passwordEncoder.encode("Senha1234*");

                User admin = new User(login, password, UserRole.ADMIN);

                userRepository.save(admin);
            }
        };
    }
}
