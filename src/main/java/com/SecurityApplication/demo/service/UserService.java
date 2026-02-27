package com.SecurityApplication.demo.service;


import com.SecurityApplication.demo.dto.LoginDto;
import com.SecurityApplication.demo.dto.SignUpDto;
import com.SecurityApplication.demo.dto.UserDto;
import com.SecurityApplication.demo.entity.User;
import com.SecurityApplication.demo.exception.ResourceNotFoundException;
import com.SecurityApplication.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User with email "+username+"not found"));
    }

    public UserDto signUp(SignUpDto signUpDTO) {
      Optional<User> user= userRepository.findByEmail(signUpDTO.getEmail());
      if(user.isPresent())
      {
          throw new BadCredentialsException("User Already exist with email "+signUpDTO.getEmail());
      }

      User toBeCreate= modelMapper.map(signUpDTO, User.class);
      toBeCreate.setPassword(passwordEncoder.encode(toBeCreate.getPassword()));
      User savedUser=userRepository.save(toBeCreate);
      return modelMapper.map(savedUser, UserDto.class);

    }


    public User getUserByUserId(Long userId) {
        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User doesnt exist with userId "+userId));
        return user;

    }
}
