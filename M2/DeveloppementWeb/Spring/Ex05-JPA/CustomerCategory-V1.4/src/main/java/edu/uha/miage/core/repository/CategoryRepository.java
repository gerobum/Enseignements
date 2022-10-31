package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    List<Category> findAllByOrderByName();
}
