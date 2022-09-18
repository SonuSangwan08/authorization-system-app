package com.sonu.authorizationsystem.model.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@ApiModel
public class LoginResponse {
    private String token;
    private static String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
