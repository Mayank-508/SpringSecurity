package com.SecurityApplication.demo.service;


import com.SecurityApplication.demo.dto.LoginDto;
import com.SecurityApplication.demo.dto.LoginResponseDTO;
import com.SecurityApplication.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final UserService userService;

    public LoginResponseDTO login(LoginDto loginDto) {

        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );
        User user= (User) authentication.getPrincipal();
        String accessToken= jwtService.generateAccessToken(user);
        String refreshToken= jwtService.generateRefreshToken(user);

        return new LoginResponseDTO(user.getId(),accessToken,refreshToken);
    }

    public LoginResponseDTO refreshToken(String refreshToken)
    {
        Long userId= jwtService.getUserIdFromToken(refreshToken);
        User user= userService.getUserByUserId(userId);
        String accessToken= jwtService.generateAccessToken(user);
        return new LoginResponseDTO(userId, accessToken, refreshToken);
    }
}
