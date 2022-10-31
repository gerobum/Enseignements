package edu.uha.miage;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.entity.Customer;
import edu.uha.miage.core.service.CategoryService;
import edu.uha.miage.core.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
// #### V1.4 Jusqu'à présent l'ordre n'avait pas d'importance, et on
// ######### aurait pu s'en passer. Mais maintenant qu'on a besoin des 
// ######### catégories ici, l'ordre devient nécessaire.
@Order(2)
public class DemoCustomers implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoCustomers.class);

    @Autowired
    private CustomerService customerService;
    // #### V1.4 On a besoin des catégories si, comme demandé dans l'énoncé,
    // ######### il faut qu'au démarrage un client soit associé à une catégorie.
    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {
    	// #### V1.4 Séraphin n'a pas de catégorie
        createCustomer("Séraphin Lampion", null);
    	// #### V1.4 Les autres oui 
        createCustomer("Tintin", "A");
        createCustomer("Capitaine Haddock", "A");
        createCustomer("Bianca Castafiore", "C");

    }

    private void createCustomer(String customerName, String categoryName) {
        Customer customer = customerService.findByName(customerName);
        Category category = null;
        if (categoryName != null) {
             category = categoryService.findByName(categoryName);
        }
        
        if (customer == null) {
            customer = new Customer(customerName);
            if (category != null) {
                customer.setCategory(category);
            }
            customerService.save(customer);
            LOGGER.info("BDD DEMO - Création du client {}", customerName);
        } else {
            LOGGER.info("BDD DEMO - Le client {} existait déjà", customerName);
        }

    }
}
