package com.SecurityApplication.demo.service;


import com.SecurityApplication.demo.entity.User;
import com.SecurityApplication.demo.exception.ResourceNotFoundException;
import com.SecurityApplication.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

//@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User with email "+username+"not found"));
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {
      Optional<User> user= userRepository.findByEmail(signUpDTO.getEmail());
      if(user.isPresent())
      {
          throw new BadCredentialsException("User Already exist with email "+signUpDTO.getEmail());
      }

      User toBeCreate= modelMapper.map(signUpDTO, User.class);
      User savedUser=userRepository.save(toBeCreate);
      return modelMapper.map(savedUser, UserDTO.class);

    }
}
