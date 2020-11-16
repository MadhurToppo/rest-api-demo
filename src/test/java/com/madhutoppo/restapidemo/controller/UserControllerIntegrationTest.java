package com.madhutoppo.restapidemo.controller;

import com.madhutoppo.restapidemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Sql("/test.sql")
    public void getUserByIdTest() {
        ResponseEntity<User> responseEntity = testRestTemplate.getForEntity("/users/1001", User.class);

        assertAll(
                () -> assertEquals(1001, Objects.requireNonNull(responseEntity.getBody()).getUserId()),
                () -> assertEquals("Madhur", Objects.requireNonNull(responseEntity.getBody()).getUserName()),
                () -> assertEquals("toppo.madhur@gmail.com", responseEntity.getBody().getEmail()),
                () -> assertEquals("8746593846", responseEntity.getBody().getPhone()),
                () -> assertEquals("Male", responseEntity.getBody().getGender())
        );
    }

    @Test
    public void saveUser() {
        User user = new User();

        user.setUserName("Dorothy");
        user.setEmail("dorothy.brown@gmail.com");
        user.setPhone("4657382940");
        user.setGender("Female");

        HttpEntity<User> requestEntity = new HttpEntity<>(user);
        ResponseEntity<User> responseEntity = testRestTemplate.postForEntity("/users", requestEntity, User.class);

        assertAll(
                () -> assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getUserId()),
                () -> assertEquals("Dorothy", Objects.requireNonNull(responseEntity.getBody()).getUserName()),
                () -> assertEquals("dorothy.brown@gmail.com", responseEntity.getBody().getEmail()),
                () -> assertEquals("4657382940", responseEntity.getBody().getPhone()),
                () -> assertEquals("Female", responseEntity.getBody().getGender())
        );
    }
}