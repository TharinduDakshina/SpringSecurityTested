package com.dakshina.SpringSecurity.service;

import com.dakshina.SpringSecurity.entity.UserEntity;
import com.dakshina.SpringSecurity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
