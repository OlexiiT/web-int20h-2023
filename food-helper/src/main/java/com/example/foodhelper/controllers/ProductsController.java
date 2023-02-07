package com.example.foodhelper.controllers;

import com.example.foodhelper.entities.Product;
import com.example.foodhelper.entities.SessionData;
import com.example.foodhelper.entities.User;
import com.example.foodhelper.repositories.ProductsRepository;
import com.example.foodhelper.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    AuthService authService;
    ProductsRepository productsRepository;

    public ProductsController(AuthService authService, ProductsRepository productsRepository) {
        this.authService = authService;
        this.productsRepository = productsRepository;
    }

    @GetMapping
    public ResponseEntity<?> getProducts(HttpServletRequest request) throws ExecutionException, InterruptedException {
        String userId = SessionData.getUserId(request.getSession().getId());
        if (userId == null) {
            return ResponseEntity.badRequest().body("Authentification required");
        }
        User user = authService.findUser(userId);
        List<Product> products = productsRepository.getAll(user.getProducts().keySet());
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putProduct(@PathVariable String id,
                                        @RequestBody ProductAmount productAmount,
                                        HttpServletRequest request) throws ExecutionException, InterruptedException {
        String userId = SessionData.getUserId(request.getSession().getId());
        if (userId == null) {
            return ResponseEntity.badRequest().body("Authentification required");
        }
        User user = authService.findUser(userId);

        Map<String, String> products = user.getProducts();
        products.put(id, productAmount.getProductAmount());

        authService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProducts(@PathVariable String productId,
                                            HttpServletRequest request) throws ExecutionException, InterruptedException {
        String userId = SessionData.getUserId(request.getSession().getId());
        if (userId == null) {
            return ResponseEntity.badRequest().body("Authentification required");
        }
        User user = authService.findUser(userId);

        Map<String, String> products = user.getProducts();
        products.remove(productId);

        authService.updateUser(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchProducts() {

        return ResponseEntity.internalServerError().body("Function is currently unavailable");
    }

    @Data
    static class ProductAmount {
        private String productAmount;
    }
}
