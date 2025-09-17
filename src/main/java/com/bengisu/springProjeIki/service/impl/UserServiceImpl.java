package com.bengisu.springProjeIki.service.impl;

import com.bengisu.springProjeIki.dto.request.UserRequest;
import com.bengisu.springProjeIki.dto.response.UserResponse;
import com.bengisu.springProjeIki.exception.UserNotFoundException;
import com.bengisu.springProjeIki.model.User;
import com.bengisu.springProjeIki.repository.UserRepository;
import com.bengisu.springProjeIki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest)
    {
        /*
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Kullanıcı başarıyla kaydedildi.");
        return userResponse;
         */
        User user = modelMapper.map(userRequest, User.class);
        if (user.getUsername() == null || user.getUsername().isBlank())
        {
            throw new RuntimeException("Username cannot be empty!");
        }
        userRepository.save(user);
        return new UserResponse(user.getUsername(), "User created.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers()
    {
        /*
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        
        for (User u : users)
        {
            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(u.getUsername());
            userResponse.setMessage("Kullanıcılar listelendi.");
            responses.add(userResponse);
        }
        return responses;
         */
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    UserResponse response = modelMapper.map(user, UserResponse.class);
                    response.setMessage("Kullanıcılar listelendi.");
                    return response;
                })
                .toList();
    }

    @Override
    public UserResponse getUser(Long id)
    {
        User getUser = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id'ye kayıtlı kullanıcı bulunamadı. "+id));
        UserResponse userResponse = modelMapper.map(getUser, UserResponse.class);
        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRequest userRequest)
    {
        /*
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id'ye kayıtlı kullanıcı bulunamadı. "+id));
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Kullanıcı başarıyla güncellendi.");
        return userResponse;
        */
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id'ye kayıtlı kullanıcı bulunamadı: " + id));
        modelMapper.map(userRequest, user);
        User updatedUser = userRepository.save(user);
        return new UserResponse(user.getUsername(), "KUllanıcı başarıyla güncellendi.");
    }

    @Override
    @Transactional
    public void deleteUser(Long id)
    {
        /*
        userRepository.deleteById(id);
        */
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id'ye kayıtlı kullanıcı bulunamadı: " + id));
        userRepository.delete(user);
    }
}
