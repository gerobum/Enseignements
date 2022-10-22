package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    List<Category> findAllByOrderByName();
}
