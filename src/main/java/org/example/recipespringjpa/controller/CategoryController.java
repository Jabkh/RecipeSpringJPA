package org.example.recipespringjpa.controller;

import org.example.recipespringjpa.model.Category;
import org.example.recipespringjpa.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Afficher la liste des catégories
    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/category_list";
    }

    // Afficher le formulaire pour créer une nouvelle catégorie
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/category_form";
    }

    // Traiter le formulaire de création d'une nouvelle catégorie
    @PostMapping
    public String createCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    // Afficher le formulaire pour modifier une catégorie existante
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "category/category_form";
        }
        return "redirect:/categories";
    }

    // Traiter le formulaire de mise à jour d'une catégorie existante
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") UUID id, @ModelAttribute Category category) {
        categoryService.updateCategory(id, category);
        return "redirect:/categories";
    }

    // Supprimer une catégorie
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") UUID id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
