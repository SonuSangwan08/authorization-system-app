package com.sonu.authorizationsystem.exception;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String detail;

}
