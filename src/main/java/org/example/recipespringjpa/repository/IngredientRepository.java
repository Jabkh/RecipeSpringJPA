package org.example.recipespringjpa.repository;

import org.example.recipespringjpa.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    Ingredient findByName(String name);
}