package com.bengisu.springProjeIki.service.impl;

import com.bengisu.springProjeIki.dto.auth.RegisterRequest;
import com.bengisu.springProjeIki.dto.auth.RegisterResponse;
import com.bengisu.springProjeIki.jwt.JwtService;
import com.bengisu.springProjeIki.model.User;
import com.bengisu.springProjeIki.repository.UserRepository;
import com.bengisu.springProjeIki.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest)
    {
        if(userRepository.findByUserName(registerRequest.getUsername()).isPresent())
        {
            throw new RuntimeException("Username already exists.");
        }
        User user = modelMapper.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);
        return new RegisterResponse("User registered successfully", token);
    }
}
