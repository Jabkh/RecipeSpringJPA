package org.example.recipespringjpa.controller;

import org.example.recipespringjpa.model.Ingredient;
import org.example.recipespringjpa.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    // Display the list of ingredients
    @GetMapping
    public String listIngredients(Model model) {
        model.addAttribute("ingredients", ingredientService.getAllIngredients());
        return "ingredient/ingredient_list";
    }

    // Display the form to create a new ingredient
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredient/ingredient_form";
    }

    // Process the form to create a new ingredient
    @PostMapping
    public String createIngredient(@ModelAttribute Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);
        return "redirect:/ingredients";
    }

    // Display the form to update an existing ingredient
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient != null) {
            model.addAttribute("ingredient", ingredient);
            return "ingredient/ingredient_form";
        }
        return "redirect:/ingredients";
    }

    // Process the form to update an existing ingredient
    @PostMapping("/update/{id}")
    public String updateIngredient(@PathVariable("id") UUID id, @ModelAttribute Ingredient ingredient) {
        ingredientService.updateIngredient(id, ingredient);
        return "redirect:/ingredients";
    }

    // Delete an ingredient
    @PostMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable("id") UUID id) {
        ingredientService.deleteIngredient(id);
        return "redirect:/ingredients";
    }
}