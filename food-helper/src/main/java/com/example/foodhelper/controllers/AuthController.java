package com.example.foodhelper.controllers;

import com.example.foodhelper.entities.SessionData;
import com.example.foodhelper.entities.User;
import com.example.foodhelper.repositories.UserRepository;
import com.example.foodhelper.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
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
    private String SESSION_NAME = "JSESSIONID";

    public AuthController(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AuthController.LoginForm loginForm,
                                             HttpServletRequest request,
                                           HttpServletResponse response) {
        User newUser = authService.createUser(loginForm.getLogin(), loginForm.getPassword());
        try {
            User user = authService.findUser(newUser);
            if (user != null) {
                return ResponseEntity.badRequest().body("This login is not available");
            } else {
                String s = authService.saveUser(newUser);
                setSesionId(request, response, String.valueOf(newUser.getId()));
                return ResponseEntity.ok(authService.getUserNoPassword(newUser));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthController.LoginForm loginForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws ExecutionException, InterruptedException {
        String login = loginForm.getLogin();
        String password = loginForm.getPassword();
        User newUser = authService.createUser(login, password);
        User user = authService.findUser(newUser);

        if (user == null) {
            return ResponseEntity.badRequest().body("User doesn't exist");
        } else {
            if (authService.checkPasswords(user, newUser)) {
                setSesionId(request, response, String.valueOf(user.getId()));
                return ResponseEntity.ok(authService.getUserNoPassword(user));
            } else {
                return ResponseEntity.badRequest().body("Password is incorrect!");
            }
        }
    }

    private void setSesionId(HttpServletRequest request,
                             HttpServletResponse response,
                             String userId) {
        String sessionId = request.getSession().getId();
        Cookie cookie = new Cookie(SESSION_NAME, sessionId);
        cookie.setPath("/");
        response.addCookie(cookie);

        SessionData.create(sessionId, userId);
    }

    private boolean removeSessionId(HttpServletRequest request,
                                 HttpServletResponse response) {

        if (request.getCookies() == null || request.getCookies().length == 0) {
            return false;
        }
        String sessionId = null;
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals(SESSION_NAME)) {
                sessionId = cookie.getName();
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        if (SessionData.remove(sessionId) == null) {
            return false;
        }
        return true;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request,
                                         HttpServletResponse response) {
        if (removeSessionId(request, response)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("No such sessionId");
        }
    }

    @Data
    public static class LoginForm {
        private String login;
        private String password;
    }

}
