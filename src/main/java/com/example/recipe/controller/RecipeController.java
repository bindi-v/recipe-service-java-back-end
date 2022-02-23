package com.example.recipe.controller;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import com.example.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepo;

    @GetMapping("/recipes")
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> getAllRecipes() {
        System.out.println("Getting All Recipes!!");
        return recipeRepo.findAll();
    }

    @GetMapping("/recipes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Recipe getRecipeById(@PathVariable Integer id) {
        Optional<Recipe> returnVal = recipeRepo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/recipes/category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> getRecipesByCategory(@PathVariable String category) {
        System.out.println("Getting Recipes By Category!!");
        return recipeRepo.findAllRecipesByCategory(category);
    }

    @PutMapping("/recipes")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecipe(@RequestBody Recipe recipe) {

        recipeRepo.save(recipe);
    }

    @PostMapping("/recipes")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe addRecipe(@RequestBody Recipe recipe) {

        return recipeRepo.save(recipe);
    }

    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable Integer id) {

        recipeRepo.deleteById(id);
    }

}
