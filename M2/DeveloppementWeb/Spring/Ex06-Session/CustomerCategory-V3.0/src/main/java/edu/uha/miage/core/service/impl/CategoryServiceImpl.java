package edu.uha.miage.core.service.impl;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.repository.CategoryRepository;
import edu.uha.miage.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
   @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category save(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAllByOrderByName();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
