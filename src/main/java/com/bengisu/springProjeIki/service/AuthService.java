package com.bengisu.springProjeIki.service;

import com.bengisu.springProjeIki.dto.auth.RegisterRequest;
import com.bengisu.springProjeIki.dto.auth.RegisterResponse;

public interface AuthService
{
    RegisterResponse register(RegisterRequest registerRequest);
}
