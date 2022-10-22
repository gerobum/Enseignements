
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


/**
 *
 * @author yvan
 */


@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CategoryRepositoryTest {
    // #### V1.2  Du logging peut être utile
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRepositoryTest.class);
    // #### V1.2 Du logging peut être utile
    
    // #### V1.2 Injection de dépendance 
    @Autowired
    CategoryRepository categoryRepository;
    


    // #### V1.2 Des exemples sont enregistrés avant chaque test. 
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

    // #### V1.2 Test pour voir si le findByName se passe bien.
    @Test
    public void testFindByName() {
        LOGGER.info("Test findByName");
        System.out.println("Test findByName");
        assertNotNull(categoryRepository.findByName("A"));
        assertNull(categoryRepository.findByName("W"));
        saveCategory("W");
        assertNotNull(categoryRepository.findByName("W"));
    }

    // #### V1.2 Et ainsi de suite. Voir un cours sur les tests unitaires.
    @Test
    public void testCount() {
        LOGGER.info("Test Count");
        System.out.println("Test Count");
        assertEquals(3, categoryRepository.count());
        saveCategory("W");
        assertEquals(4, categoryRepository.count());
    }

    // #### V1.2 Ce ne sont que des exemples. 
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
