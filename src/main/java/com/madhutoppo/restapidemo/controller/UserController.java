package com.madhutoppo.restapidemo.controller;

import com.madhutoppo.restapidemo.entity.User;
import com.madhutoppo.restapidemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
}
