package com.example.foodhelper.repositories;

import com.example.foodhelper.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class ProductsRepository implements DataRepository{
    MainRepository<Product> mainRepository;
    String COLLECTION_NAME = "ingredients";

    public ProductsRepository(MainRepository<Product> mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public Object get(String id) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public String save(Object o) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    public List<Product> getAll(Collection<String> products) throws ExecutionException, InterruptedException {
        List<Product> list = new ArrayList<>();
        for (String productId: products) {
            Product product = mainRepository.get(COLLECTION_NAME, productId, Product.class);
            product.setId(productId);
            list.add(product);
        }
        return list;
    }
}
