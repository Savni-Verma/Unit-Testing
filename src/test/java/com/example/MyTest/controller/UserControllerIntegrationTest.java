package com.example.MyTest.controller;

import com.example.MyTest.entity.User;
import com.example.MyTest.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepo userRepo;

    @BeforeEach
    void setUp() {
        userRepo.deleteAll();
    }

    @Test
    void register_whenNewEmail_shouldReturnSavedUser() {
        User userRequest = new User();
        userRequest.setEmail("shanu@gmail.com");
        userRequest.setPassword("shanu@123");

        ResponseEntity<User> response = restTemplate.postForEntity(
                "/MyTest/register",
                userRequest,
                User.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("shanu@gmail.com", response.getBody().getEmail());

    }

    @Test
    void register_whenEmailAlreadyExists_shouldReturn500() {

        User existingUser = new User();
        existingUser.setEmail("shanu@gmail.com");
        existingUser.setPassword("shanu@123");
        userRepo.save(existingUser);

        User duplicateUser = new User();
        duplicateUser.setEmail("shanu@gmail.com");
        duplicateUser.setPassword("different@123");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/MyTest/register",
                duplicateUser,
                String.class
        );

        System.out.println("STATUS: " + response.getStatusCode());
        System.out.println("BODY: " + response.getBody());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                response.getStatusCode());
        assertEquals("Email already exists", response.getBody());
    }


    }
