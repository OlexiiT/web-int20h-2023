package com.example.foodhelper.repositories;

import com.example.foodhelper.entities.Recipe;
import com.example.foodhelper.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class RecipeRepository implements DataRepository<Recipe>{
    MainRepository<Recipe> mainRepository;
    private final String COLLECTION_NAME = "recipes";

    public RecipeRepository(MainRepository<Recipe> mainRepository) {
        this.mainRepository = mainRepository;
    }


    @Override
    public Recipe get(String id) throws ExecutionException, InterruptedException {
        return mainRepository.get(COLLECTION_NAME, id, Recipe.class);
    }

    @Override
    public String save(Recipe recipe) throws ExecutionException, InterruptedException {
        return mainRepository.save(COLLECTION_NAME, String.valueOf(recipe.getIdMeal()), recipe);
    }

    public List<Recipe> getAll() throws ExecutionException, InterruptedException {
        return mainRepository.getAll(COLLECTION_NAME, Recipe.class);
    }

    @Override
    public boolean delete(Recipe recipe) {
        return false;
    }

    @Override
    public boolean update(Recipe recipe) {
        return false;
    }
}
