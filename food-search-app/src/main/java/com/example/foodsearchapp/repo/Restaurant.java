package com.example.foodsearchapp.repo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    private String login;
    private String password;
    private int loginAttempts;
    private boolean blocked;

//    private List<Dish>;
    private Address address;

}
