package com.bengisu.springProjeIki.service;

import com.bengisu.springProjeIki.model.User;
import java.util.List;

public interface UserService
{
    User createUser(User user);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
