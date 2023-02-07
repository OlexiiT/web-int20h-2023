package com.example.foodhelper.entities;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String login;
    private Integer encryptedPassword;
}
