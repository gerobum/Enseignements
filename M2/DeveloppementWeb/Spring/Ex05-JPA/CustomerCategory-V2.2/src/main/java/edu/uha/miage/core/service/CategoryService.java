package edu.uha.miage.core.service;

import edu.uha.miage.core.entity.Category;
import java.util.List;
import java.util.Optional;


public interface CategoryService {
    Category save(Category entity);
    void delete(Long id);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category findByName(String name);
    Category getOne(Long id);
}
