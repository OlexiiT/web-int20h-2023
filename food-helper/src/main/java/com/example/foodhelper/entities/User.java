package com.example.foodhelper.entities;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String login;
    private Integer encryptedPassword;
    private List<String> products;
}
