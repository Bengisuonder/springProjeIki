package com.bengisu.springProjeIki.service;

import com.bengisu.springProjeIki.dto.request.UserRequest;
import com.bengisu.springProjeIki.dto.response.UserResponse;
import java.util.List;

public interface UserService
{
    UserResponse createUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUser(Long id);
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);
}
