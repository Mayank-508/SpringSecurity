package com.SecurityApplication.demo.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private HttpStatus statusCode;
     private LocalDateTime timeStamp;
   private String error;

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus statusCode, String message) {
        this();
        this.statusCode = statusCode;
        this.error = message;
    }
}
