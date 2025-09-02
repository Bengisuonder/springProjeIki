package com.bengisu.springProjeIki.controller;

import com.bengisu.springProjeIki.model.User;
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
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @GetMapping(path = "/list")
    List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user)
    {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
    }
}
