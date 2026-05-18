package com.example.MyTest.repository;

import com.example.MyTest.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepo userRepo;

    @Test
    void findByEmail_whenEmailExists_shouldReturnUser(){

        User user = new User();
        user.setEmail("shanu@gmail.com");
        user.setPassword("shanu@123");

        userRepo.save(user);

        Optional<User> result = userRepo.findByEmail("shanu@gmail.com");

        assertTrue(result.isPresent());

        assertEquals("shanu@gmail.com",result.get().getEmail());

        assertEquals("shanu@123",result.get().getPassword());
    }
}
