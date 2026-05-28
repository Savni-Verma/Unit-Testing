package com.example.MyTest.controller;

import com.example.MyTest.entity.User;
import com.example.MyTest.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserServiceImpl userServiceImpl;

    @Autowired
    ObjectMapper objectMapper;

    @Test
     void register_whenNewEmail_shouldReturnSavedUser() throws Exception{
       User userRequest = new User();
       userRequest.setEmail("xyz@gmail.com");
       userRequest.setPassword("1234");

       User savedUser = new User();
       savedUser.setEmail("xyz@gmail.com");
       savedUser.setPassword("1234");

       when(userServiceImpl.signIn(any(User.class)))
               .thenReturn(savedUser);

       mockMvc.perform(
               post("/MyTest/register")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(userRequest))
       )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.email").value("xyz@gmail.com"))
               .andExpect(jsonPath("$.password").value("1234"));
   }

     @Test
     void register_whenEmailAlreadyExists_shouldReturn500() throws Exception{
         User userRequest = new User();
         userRequest.setEmail("xyz@gmail.com");
         userRequest.setPassword("1234");

         when(userServiceImpl.signIn(any(User.class)))
                 .thenThrow(new RuntimeException("Email already exists"));

         mockMvc.perform(
                 post("/MyTest/register")
                         .contentType(MediaType.APPLICATION_JSON)
                         .content(objectMapper.writeValueAsString(userRequest))
         )
                 .andExpect(status().isInternalServerError())
                 .andExpect(content().string("Email already exists"));
     }
}
