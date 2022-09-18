package com.sonu.authorizationsystem.controller;

import com.sonu.authorizationsystem.model.request.LoginRequest;
import com.sonu.authorizationsystem.model.request.RegistrationRequest;
import com.sonu.authorizationsystem.model.response.LoginResponse;
import com.sonu.authorizationsystem.model.response.RegistrationResponse;
import com.sonu.authorizationsystem.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/*
Controller to register new users and authenticate them.
Login endpoint returns a valid JWT token which can be used to call other available endpoints.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Start of authentication");
        LoginResponse loginResponse = authenticationService.authenticateUser(loginRequest);
        log.info("End of authentication");

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        log.info("Start of registration");
        RegistrationResponse messageResponse = authenticationService.registerUser(registrationRequest);
        if (messageResponse.isError()) {
            log.info("Error in registration: {}", messageResponse.getMessage());
            return ResponseEntity.badRequest().body(messageResponse);
        }
        log.info("End of registration");
        return ResponseEntity.ok(messageResponse);
    }
}
