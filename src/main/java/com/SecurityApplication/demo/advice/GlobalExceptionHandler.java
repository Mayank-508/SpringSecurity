package com.SecurityApplication.demo.advice;

import com.SecurityApplication.demo.exception.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(com.SecurityApplication.demo.exception.ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotException(ResourceNotFoundException exception)
    {
        ApiError apiError=new ApiError(HttpStatus.NOT_FOUND,exception.getLocalizedMessage());
        return  new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public  ResponseEntity<ApiError> handleAuthenticationError(AuthenticationException ex)
    {
        ApiError apiError=new ApiError(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage());
        return  new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public  ResponseEntity<ApiError> handleJwtException(JwtException ex)
    {
        ApiError apiError=new ApiError(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage());
        return  new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public  ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex)
    {
        ApiError apiError=new ApiError(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        return  new ResponseEntity<>(apiError,HttpStatus.FORBIDDEN);
    }


}
