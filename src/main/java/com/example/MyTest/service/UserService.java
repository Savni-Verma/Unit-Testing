package com.example.MyTest.service;

import com.example.MyTest.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User signIn(User user);
}
