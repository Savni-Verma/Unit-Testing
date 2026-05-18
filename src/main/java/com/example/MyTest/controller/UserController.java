package com.example.MyTest.controller;


import com.example.MyTest.entity.User;
import com.example.MyTest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MyTest")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userServiceImpl.signIn(user);
    }

}
