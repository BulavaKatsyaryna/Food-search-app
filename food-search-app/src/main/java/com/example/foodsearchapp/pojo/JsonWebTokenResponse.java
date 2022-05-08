package com.example.foodsearchapp.pojo;

import lombok.Data;

import java.util.List;

@Data
public class JsonWebTokenResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> statuses;

    public JsonWebTokenResponse(String token, Long id, String username, String email, List<String> statuses) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.statuses = statuses;
    }
}