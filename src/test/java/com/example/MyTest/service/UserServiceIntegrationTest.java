package com.example.MyTest.service;

import com.example.MyTest.entity.User;
import com.example.MyTest.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserRepo userRepo;

    @Test
    void signIn_whenNewEmail_shouldSaveAndReturnUser(){
        User user = new User();
        user.setEmail("xyz@gmail.com");
        user.setPassword("1234");

        User savedUser = userServiceImpl.signIn(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals("xyz@gmail.com",savedUser.getEmail());
        assertEquals("1234",savedUser.getPassword());

    }

    @Test
    void signIn_whenEmailAlreadyExists_shouldThrowRuntimeException(){
        User existingUser = new User();
        existingUser.setEmail("xyz@gmail.com");
        existingUser.setPassword("1234");
        userRepo.save(existingUser);

        User duplicateUser = new User();
        duplicateUser.setEmail("xyz@gmail.com");
        duplicateUser.setPassword("5878");

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> userServiceImpl.signIn(duplicateUser)
        );

        assertEquals("Email already exists",ex.getMessage());

    }
}
