package edu.uha.miage.core.service.impl;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.repository.CategoryRepository;
import edu.uha.miage.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

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

    // #### V1.3 L'avantage de modifier à l'implémentation de findAll dans la couche
    // #### V1.3 service est que toute invocation à cette méthode aura pour effet
    // #### V1.3 de toujours retourner la liste dans l'ordre alphabétique. 
    // #### V1.3 Il n'y aura rien d'autres à toucher. 
    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
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
