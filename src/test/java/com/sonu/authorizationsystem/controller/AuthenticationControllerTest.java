package com.sonu.authorizationsystem.controller;

import com.sonu.authorizationsystem.model.request.LoginRequest;
import com.sonu.authorizationsystem.model.request.RegistrationRequest;
import com.sonu.authorizationsystem.model.response.LoginResponse;
import com.sonu.authorizationsystem.model.response.RegistrationResponse;
import com.sonu.authorizationsystem.service.impl.AuthenticationServiceImpl;
import com.sonu.authorizationsystem.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AuthenticationServiceImpl authenticationService;


    @Test
    void authenticateUserTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("sonu");
        loginRequest.setPassword("sonu123");
        LoginResponse loginResponse = new LoginResponse("token", 1L, "sonu", "sonu@gmail.com", Arrays.asList("ROLE_ADMIN"));
        when(authenticationService.authenticateUser(loginRequest)).thenReturn(loginResponse);
        mockMvc.perform(post("/auth/login").content(JsonUtil.toJson(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void registerUserTest() throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUserName("sonu");
        registrationRequest.setPassword("sonu123");
        registrationRequest.setEmail("sonu@gmail.com");
        registrationRequest.setRole(Collections.singleton("admin"));
        RegistrationResponse registrationResponse = new RegistrationResponse("New User registered successfully", false);
        when(authenticationService.registerUser(registrationRequest)).thenReturn(registrationResponse);
        mockMvc.perform(post("/auth/register").content(JsonUtil.toJson(registrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void registerUserUsernameErrorTest() throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUserName("sonu");
        registrationRequest.setPassword("sonu123");
        registrationRequest.setEmail("sonu@gmail.com");
        registrationRequest.setRole(Collections.singleton("admin"));
        RegistrationResponse registrationResponse = new RegistrationResponse("Username already exists", true);
        when(authenticationService.registerUser(registrationRequest)).thenReturn(registrationResponse);
        mockMvc.perform(post("/auth/register").content(JsonUtil.toJson(registrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerUserEmailErrorTest() throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUserName("sonu");
        registrationRequest.setPassword("sonu123");
        registrationRequest.setEmail("sonu@gmail.com");
        registrationRequest.setRole(Collections.singleton("admin"));
        RegistrationResponse registrationResponse = new RegistrationResponse("Email already exists", true);
        when(authenticationService.registerUser(registrationRequest)).thenReturn(registrationResponse);
        mockMvc.perform(post("/auth/register").content(JsonUtil.toJson(registrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}