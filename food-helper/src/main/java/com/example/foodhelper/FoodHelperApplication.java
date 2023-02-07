package com.example.foodhelper;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class FoodHelperApplication {

    public static void main(String[] args) {
        try {
            connectToFirebase();
        } catch (IOException e) {
            System.out.println("Connection to firebase failed: " + e.getMessage());
        }
        SpringApplication.run(FoodHelperApplication.class, args);
    }

    static void connectToFirebase() throws IOException {
        if (!FirebaseApp.getApps().isEmpty()) return;
        ClassLoader classLoader = FoodHelperApplication.class.getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json"))
                .getFile().replaceAll("%20", " "));
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

        FirebaseApp.initializeApp(options);
    }
}
