package com.example.foodhelper.authentification;

import com.example.foodhelper.entities.User;
import com.example.foodhelper.repositories.UserRepository;
import com.example.foodhelper.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

@Controller
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

        /*User user = userRepository.get("user0");
        System.out.println(user);
        if (user == null) return ResponseEntity.notFound().build();*/
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String login,
                                           @RequestParam String password) {
        User newUser = authService.createUser(login, password);
        User user = null;
        try {
            user = authService.findUser(newUser);
            if (user != null) {
                return ResponseEntity.ok("This login is not available");
            } else {
                String s = authService.saveUser(newUser);
                return ResponseEntity.ok(s);
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String login,
                                        @RequestParam String password) throws ExecutionException, InterruptedException {
        User newUser = authService.createUser(login, password);
        User user = authService.findUser(newUser);

        if (user == null) {
            return ResponseEntity.ok("User doesn't exist");
        } else {
            if (authService.checkPasswords(user, newUser)) {
                return ResponseEntity.ok("Password is correct!");
            } else {
                return ResponseEntity.ok("Password is incorrect!");
            }
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("This feature is under development!");
    }

}
