package com.example.foodsearchapp.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class RegistrationRequest {

    private String username;
    private String email;
    private Set<String> statuses;
    private String password;

}
