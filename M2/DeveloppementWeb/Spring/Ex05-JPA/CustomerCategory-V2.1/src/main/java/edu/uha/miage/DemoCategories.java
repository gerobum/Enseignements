package edu.uha.miage;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Order(1)
@Component
// #### V2.1
@Profile("dev")
public class DemoCategories implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoCategories.class);

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {

        createCategory("A");
        createCategory("B");
        createCategory("Z");
    }

    private void createCategory(String name) {
        Category c = categoryService.findByName(name);
        if (c == null) {
            c = new Category(name);
            categoryService.save(c);
            LOGGER.info("BDD DEMO - Création de la catégorie {}", name);
        } else {
            LOGGER.info("BDD DEMO - La catégorie {} existait déjà", name);
        }
    }
}
