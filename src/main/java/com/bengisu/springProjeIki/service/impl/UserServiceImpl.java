package com.bengisu.springProjeIki.service.impl;

import com.bengisu.springProjeIki.dto.request.UserRequest;
import com.bengisu.springProjeIki.dto.response.UserResponse;
import com.bengisu.springProjeIki.model.User;
import com.bengisu.springProjeIki.repository.UserRepository;
import com.bengisu.springProjeIki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
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
        if (user.getUserName() == null || user.getUserName().isBlank())
        {
           throw new RuntimeException("Username cannot be empty!");
        }
        userRepository.save(user);
        return new UserResponse(user.getUserName(), "User created.");
    }

    @Override
    public List<UserResponse> getAllUsers()
    {
        /*
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        
        for (User u : users)
        {
            UserResponse userResponse = new UserResponse();
            userResponse.setUserName(u.getUserName());
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
    public UserResponse updateUser(Long id, UserRequest userRequest)
    {
        /*
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id'ye kayıtlı kullanıcı bulunamadı. "+id));
        user.setUserName(userRequest.getUserName());
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
        return new UserResponse(user.getUserName(), "KUllanıcı başarıyla güncellendi.");
    }

    @Override
    public void deleteUser(Long id)
    {
        /*
        userRepository.deleteById(id);
        */
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id'ye kayıtlı kullanıcı bulunamadı: " + id));
        userRepository.delete(user);
    }
}
