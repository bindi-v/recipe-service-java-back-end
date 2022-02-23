package com.example.recipe.controller;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Rating;
import com.example.recipe.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RatingController {
    @Autowired
    RatingRepository ratingRepo;

    @PutMapping("/ratings")
    public void updateRating(@RequestBody Rating rating) {

        ratingRepo.save(rating);
    }

    @PostMapping("/ratings")
    @ResponseStatus(HttpStatus.CREATED)
    public Rating addRating(@RequestBody Rating rating) {

        return ratingRepo.save(rating);
    }

    @GetMapping("/ratings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rating getRatingById(@PathVariable Integer id) {
        Optional<Rating> returnVal = ratingRepo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/ratings/recipe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Rating> getRatingsByRecipe(@PathVariable Integer id) {
        return ratingRepo.findAllRatingsByRecipeId(id);
    }

    @DeleteMapping("/ratings/{id}")
    public void deleteRating(@PathVariable Integer id) {
        ratingRepo.deleteById(id);
    }
}
