package com.dakshina.SpringSecurity.controller;

import com.dakshina.SpringSecurity.entity.UserEntity;
import com.dakshina.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity user){

        System.out.println("fire register");
        System.out.println(user);
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserEntity user){
        return service.verify(user);
    }

    @GetMapping("/allUsers")
    public List<UserEntity> getAllUsers(){
        return service.getAllUsers();
    }


}
