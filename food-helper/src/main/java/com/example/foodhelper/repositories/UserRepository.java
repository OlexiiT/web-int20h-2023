package com.example.foodhelper.repositories;

import com.example.foodhelper.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository implements DataRepository<User>{
    MainRepository<User> mainRepository;
    private final String COLLECTION_NAME = "users";

    public UserRepository(MainRepository<User> mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public User get(String id) throws ExecutionException, InterruptedException {
        return mainRepository.get(COLLECTION_NAME, id, User.class);
    }

    @Override
    public String save(User user) throws ExecutionException, InterruptedException {
        return mainRepository.save(COLLECTION_NAME, String.valueOf(user.getId()), user);
    }

    public Collection<User> getAll() throws ExecutionException, InterruptedException {
        return mainRepository.getAll(COLLECTION_NAME, User.class);
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean update(User user) throws ExecutionException, InterruptedException {
        User newUser = get(String.valueOf(user.getId()));
        if (newUser == null) return false;
        save(user);
        return true;
    }
}
