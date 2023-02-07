package com.example.foodhelper.services;

import com.example.foodhelper.entities.Recipe;
import com.example.foodhelper.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RecipeService {
    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe get(String id) throws ExecutionException, InterruptedException {
        return recipeRepository.get(id);
    }

    public Collection<Recipe> getRecipes(Integer offset) throws ExecutionException, InterruptedException {
        List<Recipe> allRecipes = recipeRepository.getAll();
        Collection<Recipe> result = new ArrayList<>();

        for (int i = offset * 50; i < allRecipes.size() && i < offset * 50 + 50; i++) {
            Recipe recipe = allRecipes.get(i);
            result.add(allRecipes.get(i));
        }

        return result;
    }
}
