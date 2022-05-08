package com.example.foodsearchapp.controller;

import com.example.foodsearchapp.config.token.Utility;
import com.example.foodsearchapp.pojo.LoginRequest;
import com.example.foodsearchapp.repo.StatusRepo;
import com.example.foodsearchapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Utility utility;

    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        return null;
    }
}
