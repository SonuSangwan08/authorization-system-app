package com.sonu.authorizationsystem.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class LoginRequest {
    @NotBlank(message = "Username is mandatory!")
    private String username;

    @NotBlank(message = "Password is mandatory!")
    private String password;
}
