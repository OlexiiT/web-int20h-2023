package com.example.foodhelper.controllers;

import com.example.foodhelper.entities.Recipe;
import com.example.foodhelper.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

    private RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("")
    public ResponseEntity<Collection<Recipe>> getRecipes(@RequestParam(defaultValue = "0") Integer offset) throws ExecutionException, InterruptedException {
        Collection<Recipe> recipes = recipeService.getRecipes(offset);
        if (recipes.size() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(recipes);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<String> getRecipes(@RequestParam(defaultValue = "0") Integer offset,
                                                         @RequestParam(name = "q") String query) {

        return ResponseEntity.internalServerError().body("Function is currently unavailable");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable String id) throws ExecutionException, InterruptedException {
        Recipe recipe = recipeService.get(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        } else {
            return  ResponseEntity.ok(recipe);
        }
    }
}
