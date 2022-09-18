package com.sonu.authorizationsystem.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class BookRequest {
    @NotBlank(message = "Title is mandatory!")
    private String title;
}
