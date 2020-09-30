package com.person.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomException extends RuntimeException
{
    private static final long serialVersionUID = 1L; 
    public CustomException(String message) {
        super(message);
    }
}