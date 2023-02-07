package com.example.foodhelper.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @DeleteMapping("/{productsId}")
    public ResponseEntity<?> deleteProducts() {
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchProducts() {

        return ResponseEntity.internalServerError().body("Function is currently unavailable");
    }
}
