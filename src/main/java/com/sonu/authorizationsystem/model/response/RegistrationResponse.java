package com.sonu.authorizationsystem.model.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel
public class RegistrationResponse {
    private String message;
    private boolean isError;
}
