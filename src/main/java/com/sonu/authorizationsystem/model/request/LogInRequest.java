package com.sonu.authorizationsystem.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class LogInRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
