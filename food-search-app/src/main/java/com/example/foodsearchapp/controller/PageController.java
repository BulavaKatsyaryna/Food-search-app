package com.example.foodsearchapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PageController {

    @GetMapping("/all")
    public String allAccess() {
        return "public page";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('RESTAURANT')")
    public String userAccess() {
        return "user page";
    }

    @GetMapping("/restaurant")
    @PreAuthorize("hasRole('RESTAURANT')")
    public String restaurantAccess() {
        return "restaurant page";
    }
}