package com.example.foodhelper.services;

import com.example.foodhelper.entities.Product;
import com.example.foodhelper.repositories.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProductsService {

    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product get(String id) throws ExecutionException, InterruptedException {
        return productsRepository.get(id);
    }
}
