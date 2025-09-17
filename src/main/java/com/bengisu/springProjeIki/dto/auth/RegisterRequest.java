package com.bengisu.springProjeIki.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{
    @NotEmpty(message = "Username cannot be empty!")
    private String username;
    @NotEmpty(message = "Password cannot be empty!")
    private String password;
}
