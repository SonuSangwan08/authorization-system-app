package com.sonu.authorizationsystem.service;

import com.sonu.authorizationsystem.model.request.LoginRequest;
import com.sonu.authorizationsystem.model.request.RegistrationRequest;
import com.sonu.authorizationsystem.model.response.LoginResponse;
import com.sonu.authorizationsystem.model.response.RegistrationResponse;

public interface AuthenticationService {
    LoginResponse authenticateUser(LoginRequest logInRequest);

    RegistrationResponse registerUser(RegistrationRequest registrationRequest);
}

