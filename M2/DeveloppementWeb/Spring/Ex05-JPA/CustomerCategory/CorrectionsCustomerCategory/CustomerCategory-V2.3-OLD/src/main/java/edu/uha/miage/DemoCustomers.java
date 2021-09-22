package edu.uha.miage;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.entity.Customer;
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
// #### V2.2 Le chargement au démarrage de quelques exemples de clients dans
// #### V2.2 la BDD n'existe (heureusement) qu'en mode développement ("dev").
// #### V2.2 Ce bean ne sera instancié qu'en mode (profil) dev.
// #### V2.2 Remarque: on aurait pu écrire @Profile("!prod") pour dire que 
// #### V2.2 Ce bean sera instancié si on n'est pas en mode prod.
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
        createCustomer("Boulanger", "B");
        createCustomer("Super U", "B");
        createCustomer("Auchan", "A");
        createCustomer("Leclerc");
    }

    private void createCustomer(String customerName, String categoryName) {
        Customer customer = customerService.findByName(customerName);
        if (customer == null) {
            customer = new Customer(customerName);
            if (categoryName != null) {
                Category category = categoryService.findByName(categoryName);
                if (category != null) {
                    customer.setCategory(category);
                }
            }
            customerService.save(customer);
            LOGGER.info("BDD DEMO - Création du client {}", customerName);
        } else {
            LOGGER.info("BDD DEMO - Le client {} existait déjà", customerName);
        }
    }

    private void createCustomer(String name) {
        createCustomer(name, null);
    }
}