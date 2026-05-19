package com.example.MyTest.controller;


import com.example.MyTest.entity.User;
import com.example.MyTest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User saved = userServiceImpl.signIn(user);
            return ResponseEntity.ok(saved);                        // 200
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());                          // 500
        }
    }
}
