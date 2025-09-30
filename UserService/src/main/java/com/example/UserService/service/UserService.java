package com.example.UserService.service;

import com.example.UserService.entity.User;
import com.example.UserService.exception.UserNotExistException;
import com.example.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmailAndPassword(String email, String password) throws RuntimeException {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new UserNotExistException("Cannot found user: " + email);
        }
        return user;
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

}
