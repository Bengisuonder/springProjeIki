package com.bengisu.springProjeIki.controller;

import com.bengisu.springProjeIki.dto.request.UserRequest;
import com.bengisu.springProjeIki.dto.response.UserResponse;
import com.bengisu.springProjeIki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @PostMapping(path = "/create")
    public UserResponse createUser(@RequestBody UserRequest userRequest)
    {
        return userService.createUser(userRequest);
    }

    @GetMapping(path = "/list")
    List<UserResponse> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest)
    {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
    }
}
