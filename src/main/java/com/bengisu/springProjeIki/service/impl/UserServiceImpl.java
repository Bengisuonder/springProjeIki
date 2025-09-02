package com.bengisu.springProjeIki.service.impl;

import com.bengisu.springProjeIki.model.User;
import com.bengisu.springProjeIki.repository.UserRepository;
import com.bengisu.springProjeIki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    @Override
    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user)
    {
        return userRepository.findById(id).map( u ->
        {
            u.setUserName(user.getUserName());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            return userRepository.save(u);
        }).orElseThrow(() -> new RuntimeException("User not found with id" + id));
    }

    @Override
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }
}
