package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Category;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CategoryRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRepositoryTest.class);

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        saveCategory("A");
        saveCategory("B");
        saveCategory("Z");
    }

    private void saveCategory(String name) {
        if (categoryRepository.findByName(name) == null) {
            Category category = new Category(name);
            categoryRepository.save(category);
        }
    }

    @Test
    public void testFindByName() {
        LOGGER.info("Test findByName");
        System.out.println("Test findByName");
        assertNotNull(categoryRepository.findByName("A"));
        assertNull(categoryRepository.findByName("W"));
        saveCategory("W");
        assertNotNull(categoryRepository.findByName("W"));
    }

    @Test
    public void testCount() {
        LOGGER.info("Test Count");
        System.out.println("Test Count");
        assertEquals(3, categoryRepository.count());
        saveCategory("W");
        assertEquals(4, categoryRepository.count());
    }

    @Test
    public void testDelete() {
        LOGGER.info("Test Count");
        System.out.println("Test Count");
        Category a = categoryRepository.findByName("A");
        long n = categoryRepository.count();
        categoryRepository.delete(a);
        assertEquals(n-1, categoryRepository.count());
        categoryRepository.deleteAll();
        assertEquals(0, categoryRepository.count());
    }

}
