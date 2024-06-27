package org.example.recipespringjpa.service;

import org.example.recipespringjpa.model.Ingredient;
import org.example.recipespringjpa.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    // Get all ingredients
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // Get an ingredient by ID
    public Ingredient getIngredientById(UUID id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    // Save a new ingredient
    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    // Update an existing ingredient
    public void updateIngredient(UUID id, Ingredient updatedIngredient) {
        if (ingredientRepository.existsById(id)) {
            updatedIngredient.setId(id);
            ingredientRepository.save(updatedIngredient);
        }
    }

    // Delete an ingredient by ID
    public void deleteIngredient(UUID id) {
        ingredientRepository.deleteById(id);
    }

    // Find an ingredient by its name
    public Ingredient findByName(String name) {
        return ingredientRepository.findByName(name);
    }

}