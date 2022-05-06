package com.example.foodsearchapp.pojo;

import lombok.Data;

@Data
public class MessResponse {

    private String message;

    public MessResponse(String message) {
        this.message = message;
    }
}
