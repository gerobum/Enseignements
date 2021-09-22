package edu.uha.miage;

import edu.uha.miage.core.entity.Groupe;
import edu.uha.miage.core.entity.Etudiant;
import edu.uha.miage.core.service.CategoryService;
import edu.uha.miage.core.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// #### V1.1 Demo est une implémentation de CommandLineRunner qui indique que
// #### V1.1 c'est un Bean qui doit être exécuté à son lancement.
// #### V1.1 Plusieurs CommandLineRunner peuvent coexister. Si un ordre 
// #### V1.1 d'exécution est nécessaire, il peut être indiqué par @Order
@Component
@Order(2)
@Profile("dev")
public class DemoCustomers implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoCustomers.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {
        createCustomer("Client 1", null);
        createCustomer("Client 2", "B");

    }

    private void createCustomer(String customerName, String categoryName) {
        Groupe groupe = customerService.findByName(customerName);
        Etudiant etudiant = null;
        if (categoryName != null) {
             etudiant = categoryService.findByName(categoryName);
        }
        
        if (groupe == null) {
            groupe = new Groupe(customerName);
            if (etudiant != null) {
                groupe.setCategory(etudiant);
            }
            customerService.save(groupe);
            LOGGER.info("BDD DEMO - Création du client {}", customerName);
        } else {
            LOGGER.info("BDD DEMO - Le client {} existait déjà", customerName);
        }

    }
}
