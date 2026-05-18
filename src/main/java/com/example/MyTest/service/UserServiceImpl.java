package com.example.MyTest.service;

import com.example.MyTest.entity.User;
import com.example.MyTest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User signIn(User user) {
        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new RuntimeException("Email already exists");
        }
        return userRepo.save(user);
    }

}
