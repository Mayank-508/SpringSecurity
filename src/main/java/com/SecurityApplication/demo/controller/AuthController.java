package com.SecurityApplication.demo.controller;

import com.SecurityApplication.demo.dto.LoginDto;
import com.SecurityApplication.demo.dto.SignUpDto;
import com.SecurityApplication.demo.dto.UserDto;
import com.SecurityApplication.demo.service.AuthService;
import com.SecurityApplication.demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto)
    {
        UserDto userDto= userService.signUp(signUpDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletResponse response)
    {
       String token= authService.login(loginDto);

        Cookie cookie= new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
       return ResponseEntity.ok()
               .header(HttpHeaders.SET_COOKIE)
               .body(token);
    }




}
