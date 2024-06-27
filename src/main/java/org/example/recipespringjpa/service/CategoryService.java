package org.example.recipespringjpa.service;

import org.example.recipespringjpa.model.Category;
import org.example.recipespringjpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Obtenir toutes les catégories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Obtenir une catégorie par ID
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Enregistrer une nouvelle catégorie
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    // Mettre à jour une catégorie existante
    public void updateCategory(UUID id, Category updatedCategory) {
        if (categoryRepository.existsById(id)) {
            updatedCategory.setId(id);
            categoryRepository.save(updatedCategory);
        }
    }

    // Supprimer une catégorie par ID
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

//    // Trouver une catégorie par son nom
//    public Category findByName(String name) {
//        return categoryRepository.findByName(name);
//    }
}
