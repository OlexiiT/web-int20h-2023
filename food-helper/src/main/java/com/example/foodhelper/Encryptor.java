package com.example.foodhelper;

public class Encryptor {
    public int encryptPassword(String password) {
        return password.hashCode();
    }
}
