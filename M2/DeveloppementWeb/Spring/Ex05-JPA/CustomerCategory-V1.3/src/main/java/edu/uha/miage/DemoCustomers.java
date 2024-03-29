package edu.uha.miage;

import edu.uha.miage.core.entity.Customer;
import edu.uha.miage.core.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Order(2)
public class DemoCustomers implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoCustomers.class);

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {
        createCustomer("Capitaine Haddock");
        createCustomer("Tintin");
        createCustomer("Milou");
        createCustomer("Professeur Tournesol");

    }

    private void createCustomer(String name) {
        Customer c = customerService.findByName(name);
        if (c == null) {
            c = new Customer(name);
            customerService.save(c);
            LOGGER.info("BDD DEMO - Création du client {}", name);
        } else {
            LOGGER.info("BDD DEMO - Le client {} existait déjà", name);
        }
    }
}
