package com.example.recipe.controller;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.PrepStep;
import com.example.recipe.repository.PrepStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PrepStepController {

    @Autowired
    PrepStepRepository prepStepRepo;

    @PutMapping("/prepsteps")
    @ResponseStatus(HttpStatus.OK)
    public void updatePrepSteps(@RequestBody PrepStep prepStep) {

        prepStepRepo.save(prepStep);
    }

    @PostMapping("/prepsteps")
    @ResponseStatus(HttpStatus.CREATED)
    public PrepStep addPrepSteps(@RequestBody PrepStep prepStep) {

        return prepStepRepo.save(prepStep);
    }

    @GetMapping("/prepsteps/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PrepStep getPrepStepsById(@PathVariable Integer id) {
        Optional<PrepStep> returnVal = prepStepRepo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }

    }

    @GetMapping("/prepsteps/recipe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PrepStep> getPrepStepsByRecipe(@PathVariable Integer id) {
        return prepStepRepo.findAllPrepStepsByRecipeId(id);
    }

    @DeleteMapping("/prepsteps/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePrepSteps(@PathVariable Integer id) {

        prepStepRepo.deleteById(id);
    }
}
