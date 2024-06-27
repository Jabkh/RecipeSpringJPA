package org.example.recipespringjpa.service;

import org.example.recipespringjpa.model.Recipe;
import org.example.recipespringjpa.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(UUID id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void updateRecipe(UUID id, Recipe updatedRecipe) {
        if (recipeRepository.existsById(id)) {
            updatedRecipe.setId(id);
            recipeRepository.save(updatedRecipe);
        }
    }

    public void deleteRecipe(UUID id) {
        recipeRepository.deleteById(id);
    }

     public List<Recipe> getByName(String query) {
         return recipeRepository.findByNameContainingIgnoreCase(query);
     }
}