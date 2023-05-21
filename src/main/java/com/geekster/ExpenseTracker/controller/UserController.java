package com.geekster.ExpenseTracker.controller;

import com.geekster.ExpenseTracker.Service.UserService;
import com.geekster.ExpenseTracker.model.User;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1/user")
public class UserController {
    @Autowired
    UserService service;
    @GetMapping(value ="getUser")
    private ResponseEntity<List<User>> getUser(@Nullable @RequestParam Integer userId){
        List<User> response = service.getUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value ="signUp")
    private ResponseEntity<User> signUp(@Valid @RequestBody User user){
        User response = service.signUp(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value ="logIn")
    private ResponseEntity<String> login(@Valid @RequestParam String userName, @Valid @RequestParam String userPassword){
        String response = service.logIn(userName, userPassword);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value ="deleteUser")
    private ResponseEntity<String> deleteUser(@RequestParam Integer userId) {
        String response = service.deleteUser(userId);
        if (response == null)
            return new ResponseEntity<>("User not exist", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }
}