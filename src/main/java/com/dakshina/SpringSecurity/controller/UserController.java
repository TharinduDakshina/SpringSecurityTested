package com.dakshina.SpringSecurity.controller;

import com.dakshina.SpringSecurity.entity.UserEntity;
import com.dakshina.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    private List<UserEntity> usersInSystem = new ArrayList<>(
            List.of(
                    new UserEntity( "Navin", "N@123"),
                    new UserEntity( "Kiran", "K@123")
            ));

    @PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity user){
        System.out.println("fire register");
        System.out.println(user.getUserName());
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
