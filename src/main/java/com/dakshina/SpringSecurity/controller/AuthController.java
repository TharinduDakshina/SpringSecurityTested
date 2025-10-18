package com.dakshina.SpringSecurity.controller;

import com.dakshina.SpringSecurity.entity.UserEntity;
import com.dakshina.SpringSecurity.service.AuthService;
import com.dakshina.SpringSecurity.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    private  UserService userService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return  userService.getAllUsers();
    }
}
