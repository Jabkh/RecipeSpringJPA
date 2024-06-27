package org.example.recipespringjpa.repository;

import org.example.recipespringjpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findByName(String name);
}
