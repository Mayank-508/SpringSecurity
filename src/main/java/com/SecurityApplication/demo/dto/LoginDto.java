package com.SecurityApplication.demo.dto;

import jakarta.persistence.Column;
import jdk.jshell.EvalException;
import lombok.Data;

@Data
public class LoginDto {

    @Column(unique = true)
    private String email;
    private String password;

}
