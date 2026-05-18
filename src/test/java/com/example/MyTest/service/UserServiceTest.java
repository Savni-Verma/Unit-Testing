package com.example.MyTest.service;

import com.example.MyTest.entity.User;
import com.example.MyTest.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepo userRepo;

    @Test
    void userIsRegisterSucessfully() {

        User user = new User();
        user.setEmail("shavni@gmail.com");
        user.setPassword("shavni@123");

        when(userRepo.findByEmail("shavni@gmail.com")).thenReturn(Optional.empty());

        when(userRepo.save(user)).thenReturn(user);

        User result = userService.signIn(user);
        assertNotNull(result);
        assertEquals(user,result);
        assertEquals("shavni@gmail.com",result.getEmail());

    }

    @Test
    void userIsNotRegisterSuccessfully(){
        User user = new User();
        user.setEmail("xyz@gmail.com");
        user.setPassword("xyz@123");

        when(userRepo.findByEmail("xyz@gmail.com")).thenReturn(Optional.of(user));

        RuntimeException ex =
                assertThrows(RuntimeException.class , () ->
    userService.signIn(user));

        assertEquals("Email already exists",ex.getMessage());

    }

}
