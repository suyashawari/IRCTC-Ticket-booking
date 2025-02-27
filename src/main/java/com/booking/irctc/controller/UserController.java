package com.booking.irctc.controller;

import com.booking.irctc.entity.UserData;
import com.booking.irctc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signupUser(@RequestBody UserData userData){
        if (userService.signup(userData))
            return new ResponseEntity<>(true, HttpStatus.OK);
        else return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);


    }
    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody UserData userData){
        if (userService.logIn(userData))
            return new ResponseEntity<>(true, HttpStatus.OK);
        else return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);


    }
}
