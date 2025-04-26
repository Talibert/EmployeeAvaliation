package com.example.employeeperformance.controller;

import com.example.employeeperformance.VOs.AuthVO;
import com.example.employeeperformance.VOs.RegisterVO;
import com.example.employeeperformance.entities.User;
import com.example.employeeperformance.services.security.AuthService;
import com.example.employeeperformance.services.security.TokenService;
import com.example.employeeperformance.types.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private AuthService authService;

    @MockBean
    private TokenService tokenService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Vamos fazer login corretamente
     */
    @Test
    void testeLogin() throws Exception {

        AuthVO authVO = new AuthVO("usuario", "senha");
        User user = new User("usuario", "senha", UserRole.USER);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);

        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);
        Mockito.when(tokenService.generateToken(user)).thenReturn("token-de-teste");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authVO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token-de-teste"));
    }

    /**
     * Vamos tentar criar um usuário que ainda não existe
     */
    @Test
    void testeRegister() throws Exception {
        Mockito.when(authService.loadUserByUsername("usuario")).thenReturn(null);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getRegisterVO())))
                .andExpect(status().isOk());
    }

    /**
     * Vamos tentar criar um usuário que já existe
     */
    @Test
    void testeRegisterUsuarioJaExistente() throws Exception {
        Mockito.when(authService.loadUserByUsername("usuario")).thenReturn(new User());

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getRegisterVO())))
                .andExpect(status().isBadRequest());
    }

    private RegisterVO getRegisterVO(){
        return new RegisterVO("usuario", "senha", UserRole.USER);
    }
}