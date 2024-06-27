package org.example.recipespringjpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Quantity is mandatory")
    private String quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, targetEntity = Recipe.class)
    @JoinColumn(name = "recipe_id", nullable = true)
    private Recipe recipe;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Quantity is mandatory") String getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotBlank(message = "Quantity is mandatory") String quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
