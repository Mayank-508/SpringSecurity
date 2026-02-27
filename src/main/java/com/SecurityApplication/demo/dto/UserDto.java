package com.SecurityApplication.demo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {



    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
}
