package com.dakshina.SpringSecurity.service;

import com.dakshina.SpringSecurity.entity.UserEntity;
import com.dakshina.SpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository repository;

    private final BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);

    public UserEntity register(UserEntity user){
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return user;
    }

    public String verify(UserEntity user){
        Authentication authentication =authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(),user.getPassword()
                )
        );

        if (authentication.isAuthenticated()){
            return jwtService.getJWTToken(user.getUserName());
        }else {
            return "fail";
        }
    }

    public List<UserEntity> getAllUsers(){
        return repository.findAll();
    }
}
