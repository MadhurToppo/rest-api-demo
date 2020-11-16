package com.madhutoppo.restapidemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madhutoppo.restapidemo.entity.User;
import com.madhutoppo.restapidemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUserByIdTest() throws Exception {

        // Mock the data returned by the user service class
        User user = new User();
        user.setUserName("Madhur");
        user.setEmail("toppo.madhur@gmail.com");
        user.setPhone("9874287467");
        user.setGender("Male");

        when(userService.getUserById(anyInt())).thenReturn(user);

        // Create a mock HTTP request to verify the expected result
        mockMvc.perform(MockMvcRequestBuilders.get("/users/12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("Madhur"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("toppo.madhur@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("9874287467"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("Male"));
    }

    @Test
    public void saveUserTest() throws Exception {

        // Mock the user data that we have to save
        User user = new User();
        user.setUserId(1);
        user.setUserName("Mukul");
        user.setEmail("toppo.mukul@gmail.com");
        user.setPhone("7623858937");
        user.setGender("Male");

        // Mock the behavior (when userService saves user the user is being returned)
        when(userService.saveUser(any(User.class))).thenReturn(user);


        // Mock the request "/users"
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("Mukul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("toppo.mukul@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("7623858937"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("Male"));

    }
}