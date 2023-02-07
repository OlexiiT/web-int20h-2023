package com.example.foodhelper.controllers;

import com.example.foodhelper.entities.Product;
import com.example.foodhelper.entities.Recipe;
import com.example.foodhelper.entities.SessionData;
import com.example.foodhelper.entities.User;
import com.example.foodhelper.services.AuthService;
import com.example.foodhelper.services.ProductsService;
import com.example.foodhelper.services.RecipeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
public class SearchController {
    private RecipeService recipeService;
    private ProductsService productsService;
    private AuthService authService;

    public SearchController(RecipeService recipeService, ProductsService productsService, AuthService authService) {
        this.recipeService = recipeService;
        this.productsService = productsService;
        this.authService = authService;
    }

    @GetMapping("/recipes/match")
    public ResponseEntity<?> selectRecipes(HttpServletRequest request,
                                           @RequestBody Object productsBody) throws ExecutionException, InterruptedException {
        String userId = SessionData.getUserId(request.getSession().getId());
        if (userId == null) {
            return selectRecipes(productsBody);
        }
        User user = authService.findUser(userId);

        List<String> products;
        if (user.getProducts() == null) products = null;
        else {
            products = new ArrayList<>(user.getProducts().keySet());
        }

        return ResponseEntity.ok(selectAvailable(products));
    }

    public ResponseEntity<?> selectRecipes(@RequestBody Object products) throws ExecutionException, InterruptedException {

        List list = (ArrayList) ((LinkedHashMap<?, ?>) products).get("products");
        List<String> prods = new ArrayList<>();
        for (Object o : list) {
            String productId = o.toString();
            Product product = productsService.get(productId);
            prods.add(product.getName());
        }
        return ResponseEntity.ok(selectAvailable(prods));
    }

    private List<Recipe> selectAvailable(List<String> products) throws ExecutionException, InterruptedException {
        List<Recipe> recipes = new ArrayList<>();
        List<Recipe> allRecipes = (List) recipeService.getAllRecipes();

        for (Recipe recipe : allRecipes) {
            if (isAvailable(recipe, products)) recipes.add(recipe);
        }

        return recipes;
    }

    private boolean isAvailable(Recipe recipe, List<String> products) {
        if (products == null || recipe.getIngredients() == null) return false;
        mark1:
        for (String product : products) {
            for (Object o : recipe.getIngredients()) {
                if (((HashMap<String, String>) o).get("ingredient").equals(product)) {
                    continue mark1;
                }
            }
            return false;
        }
        return true;
    }
}
