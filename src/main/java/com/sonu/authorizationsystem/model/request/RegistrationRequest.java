package com.sonu.authorizationsystem.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@ApiModel
public class RegistrationRequest {
    @NotBlank(message = "UserName is mandatory!")
    @Size(min = 3, max = 20, message = "UserName should be between 3 to 29 character long!")
    private String userName;

    @NotBlank(message = "Email is mandatory!")
    @Size(max = 50)
    @Email(message = "Not Valid!")
    private String email;

    private Set<String> role;

    @NotBlank(message = "Password is mandatory!")
    @Size(min = 6, max = 40)
    private String password;
}
