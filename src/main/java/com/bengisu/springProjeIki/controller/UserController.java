package com.bengisu.springProjeIki.controller;

import com.bengisu.springProjeIki.dto.auth.RegisterRequest;
import com.bengisu.springProjeIki.dto.auth.RegisterResponse;
import com.bengisu.springProjeIki.dto.request.UserRequest;
import com.bengisu.springProjeIki.dto.response.UserResponse;
import com.bengisu.springProjeIki.service.impl.AuthServiceImpl;
import com.bengisu.springProjeIki.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserServiceImpl userService;
    private final AuthServiceImpl authService;
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(path = "/create")
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest)
    {
        logger.info("User created.");
        return userService.createUser(userRequest);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request)
    {
        RegisterResponse response = authService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/list")
    List<UserResponse> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id)
    {
        return userService.getUser(id);
    }

    @PutMapping("/update/{id}")
    public UserResponse updateUser(@Valid @PathVariable Long id, @RequestBody UserRequest userRequest)
    {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
    }
}
