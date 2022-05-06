package com.example.foodsearchapp.pojo;

import com.example.foodsearchapp.model.Status;
import lombok.Data;

import java.util.List;

@Data
public class JsonWebTokenResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<Status> statuses;

    public JsonWebTokenResponse(String token, Long id, String username, String email, List<Status> statuses) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.statuses = statuses;
    }
}