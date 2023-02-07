package com.example.foodhelper.controllers;

import com.example.foodhelper.entities.User;
import com.example.foodhelper.repositories.UserRepository;
import com.example.foodhelper.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    UserRepository userRepository;
    AuthService authService;

    public AuthController(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @GetMapping("/")
    public ResponseEntity<Collection<User>> index() throws ExecutionException, InterruptedException {
        Collection<User> users = userRepository.getAll();
        System.out.println(users);

        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestParam String login,
                                             @RequestParam String password,
                                             HttpServletRequest request) {
        User newUser = authService.createUser(login, password);
        User user = null;
        try {
            user = authService.findUser(newUser);
            if (user != null) {
                return ResponseEntity.badRequest().body("This login is not available");
            } else {
                String s = authService.saveUser(newUser);
                return ResponseEntity.ok(authService.getUserNoPassword(newUser));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String login,
                                        @RequestParam String password,
                                          HttpServletRequest request) throws ExecutionException, InterruptedException {
        User newUser = authService.createUser(login, password);
        User user = authService.findUser(newUser);

        if (user == null) {
            return ResponseEntity.badRequest().body("User doesn't exist");
        } else {
            if (authService.checkPasswords(user, newUser)) {
                return ResponseEntity.ok(authService.getUserNoPassword(user));
            } else {
                return ResponseEntity.badRequest().body("Password is incorrect!");
            }
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("This feature is under development!");
    }

}
