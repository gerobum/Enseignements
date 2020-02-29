package fr.miage.core.service.impl;

import fr.miage.core.entity.Category;
import fr.miage.core.repository.CategoryRepository;
import fr.miage.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// #### V1.0 @Service est une annotation qui spécialise @Component.
// #### V1.0 Elle indique une classe métier.
// #### V1.0 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html
@Service
public class CategoryServiceImpl implements CategoryService {

// #### V1.0 @Autowired permet d'injecter automatiquement la bonne dépendance.
// #### V1.0 Elle est fabriquée automatiquement par Spring (voir CategoryRepository)
    @Autowired
    CategoryRepository categoryRepository;

// #### V1.0 Le reste s'écrit facilement.
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

    @Override
    public Category getOne(Long id) {
        return categoryRepository.getOne(id);
    }
}
