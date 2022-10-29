package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
// #### V1.0 Couche repository
// ######### Mêmes choses que pour Customer
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
