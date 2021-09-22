package edu.uha.miage.core.service;

import edu.uha.miage.core.entity.Category;
import java.util.List;


// #### V0.1 On y définit les méthodes dont on a besoin.
// #### V0.1 Cette interface nécessite une implémentation, mais elle est très
// #### V0.1 simple à faire. Voir le paquetage impl
public interface CategoryService {
    Category save(Category entity);
    void delete(String id);
    List<Category> findAll();
    Category findByName(String name);
}
