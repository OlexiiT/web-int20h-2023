package com.example.foodhelper.repositories;

import java.util.concurrent.ExecutionException;

public interface DataRepository<T> {
    T get(String id) throws ExecutionException, InterruptedException;
    String save(T t) throws ExecutionException, InterruptedException;
    boolean delete(T t);
    boolean update(T t) throws ExecutionException, InterruptedException;
}
