package com.SecurityApplication.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseDTO {

    private Long id;
    private String accessToken;
    private String refreshToken;

}
