package com.booking.irctc.controller;

import com.booking.irctc.entity.UserData;
import com.booking.irctc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Boolean> loginUser(@RequestBody UserData userData) {
        if (userService.logIn(userData))
            return new ResponseEntity<>(true, HttpStatus.OK);
        else return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/auth/me")
    public String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "No user is logged in.";
        }
        return "Logged-in user: " + authentication.getName();
    }



}
