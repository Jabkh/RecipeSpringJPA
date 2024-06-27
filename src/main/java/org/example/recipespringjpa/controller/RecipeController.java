package org.example.recipespringjpa.controller;

import jakarta.validation.Valid;
import org.example.recipespringjpa.model.Recipe;
import org.example.recipespringjpa.service.CategoryService;
import org.example.recipespringjpa.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipe/recipes";
    }

    @GetMapping("/{recipeId}")
    public String detailPage(@PathVariable("recipeId") String recipeId, Model model) {
        try {
            UUID id = UUID.fromString(recipeId);
            Recipe recipe = recipeService.getRecipeById(id);
            if (recipe != null) {
                model.addAttribute("recipe", recipe);
                return "recipe/recipe_detail";
            }
            return "redirect:/recipes";
        } catch (IllegalArgumentException e) {
            return "redirect:/recipes";
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "recipe/recipe_form";
    }

    @PostMapping
    public String createRecipe(@Valid @ModelAttribute Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "recipe/recipe_form";
        }
        recipeService.saveRecipe(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/edit/{recipeId}")
    public String showEditForm(@PathVariable("recipeId") UUID recipeId, Model model) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "recipe/recipe_form";
        }
        return "redirect:/recipes";
    }

    @PostMapping("/update/{recipeId}")
    public String updateRecipe(@PathVariable("recipeId") UUID recipeId, @Valid @ModelAttribute Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "recipe/recipe_form";
        }
        recipe.setId(recipeId);
        recipeService.updateRecipe(recipeId, recipe);
        return "redirect:/recipes";
    }

    @PostMapping("/delete/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") UUID recipeId) {
        recipeService.deleteRecipe(recipeId);
        return "redirect:/recipes";
    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "recipe/search";
    }

    @PostMapping("/search")
    public String searchRecipe(@RequestParam("query") String query, Model model) {
        List<Recipe> recipes = recipeService.getByName(query);
        model.addAttribute("recipes", recipes);
        return "recipe/search_results";
    }
}