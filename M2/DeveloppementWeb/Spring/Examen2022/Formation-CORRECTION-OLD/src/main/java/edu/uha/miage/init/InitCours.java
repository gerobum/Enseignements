package edu.uha.miage.init;


import edu.uha.miage.core.entity.Cours;
import edu.uha.miage.core.entity.Enseignant;
import edu.uha.miage.core.repository.CoursRepository;
import edu.uha.miage.core.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Order(2)
public class InitCours implements CommandLineRunner {

    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private CoursRepository coursRepository;

    private Random R = new Random();

    @Override
    public void run(String... args) throws Exception {
        List<Enseignant> enseignants = enseignantRepository.findAll();
        coursRepository.save(new Cours("Génie Logiciel", 40, enseignants.get(R.nextInt(enseignants.size()))));
        coursRepository.save(new Cours("Java", 20, enseignants.get(R.nextInt(enseignants.size()))));
        coursRepository.save(new Cours("Base de données", 35, enseignants.get(R.nextInt(enseignants.size()))));
        coursRepository.save(new Cours("Latex", 10, enseignants.get(R.nextInt(enseignants.size()))));
        coursRepository.save(new Cours("Structure de données", 38, enseignants.get(R.nextInt(enseignants.size()))));
    }
/*
    private static final Logger LOGGER = LoggerFactory.getLogger(InitCours.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {
        createCustomer("Séraphin Lampion", null);
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

    }*/
}
