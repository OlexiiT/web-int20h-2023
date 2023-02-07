package com.example.foodhelper.services;

import com.example.foodhelper.Encryptor;
import com.example.foodhelper.entities.User;
import com.example.foodhelper.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String login, String password) {
        User user = new User();
        user.setId(login.hashCode());
        user.setLogin(login);
        user.setEncryptedPassword(new Encryptor().encryptPassword(password));
        return user;
    }

    public User findUser(User user) throws ExecutionException, InterruptedException {
        return userRepository.get(String.valueOf(user.getId()));
    }

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        return userRepository.save(user);
    }

    public boolean checkPasswords(User user, User newUser) {
        return user.getEncryptedPassword().equals(newUser.getEncryptedPassword());
    }

    public User getUserNoPassword(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setLogin(user.getLogin());
        newUser.setProducts(user.getProducts());
        return newUser;
    }
}
