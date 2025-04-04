package com.example.employeeperformance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // exige autenticação para tudo
                )
                .httpBasic() // ativa BasicAuth (usuário/senha)
                .and()
                .csrf().disable(); // CSRF desativado para facilitar testes com curl/postman

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin") // 🧑‍💼 usuário
                .password("1234")  // 🔐 senha
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
