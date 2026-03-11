package com.SecurityApplication.demo.dto;


import com.SecurityApplication.demo.entity.enums.Permissions;
import com.SecurityApplication.demo.entity.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {
    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
    private Set<Permissions> permissions;
}
