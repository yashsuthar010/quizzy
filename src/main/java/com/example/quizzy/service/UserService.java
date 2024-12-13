package com.example.quizzy.service;

import com.example.quizzy.model.User;
import com.example.quizzy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(){
        return userRepository.findUser().orElse(null);
    }
}
