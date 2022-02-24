package com.example.recipe.controller;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import com.example.recipe.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IngredientController {
    @Autowired
    IngredientRepository repo;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/ingredients")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return repo.save(ingredient);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredient(@PathVariable Integer id) {
        Optional<Ingredient> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/ingredients/recipe/{id}")
    public List<Ingredient> getIngredientsByRecipe(@PathVariable Integer id) {
        return repo.findAllIngredientsByRecipeId(id);
    }

    @CrossOrigin("http://localhost:3000")
    @PutMapping("/ingredients")
    public void updateIngredient(@RequestBody Ingredient ingredient) {
        repo.save(ingredient);
    }

    @CrossOrigin("http://localhost:3000")
    @DeleteMapping("/ingredients/{id}")
    public void deleteIngredient(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
