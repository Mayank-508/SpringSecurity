package com.SecurityApplication.demo.controller;

import com.SecurityApplication.demo.dto.LoginDto;
import com.SecurityApplication.demo.dto.LoginResponseDTO;
import com.SecurityApplication.demo.dto.SignUpDto;
import com.SecurityApplication.demo.dto.UserDto;
import com.SecurityApplication.demo.service.AuthService;
import com.SecurityApplication.demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Value("deploy.env")
    private String deployEnv;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto)
    {
        UserDto userDto= userService.signUp(signUpDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDto loginDto, HttpServletResponse response)
    {
       LoginResponseDTO loginResponseDTO= authService.login(loginDto);

        Cookie cookie= new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        response.addCookie(cookie);

       return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh( HttpServletRequest request)
    {
        String refreshToken = Arrays.stream(request.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthenticationServiceException("Refresh token not found inside the cookies"));
        LoginResponseDTO loginResponseDTO =authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDTO);
    }




}
