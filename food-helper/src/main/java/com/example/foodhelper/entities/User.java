package com.example.foodhelper.entities;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class User {
    private Integer id;
    private String login;
    private Integer encryptedPassword;
    private Map<String, String> products;

    public User() {
        this.products = products;
    }
}
