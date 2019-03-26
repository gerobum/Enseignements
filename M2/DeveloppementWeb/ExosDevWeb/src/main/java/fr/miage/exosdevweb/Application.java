package fr.miage.exosdevweb;

import fr.miage.exosdevweb.dao.PersonDAO;
import fr.miage.exosdevweb.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(PersonDAO dao) {
        return (args) -> {
            // save a couple of customers
            dao.save(new Person("Jack", 50));
            dao.save(new Person("Chloe", 48));
            dao.save(new Person("Kim", 30));
            dao.save(new Person("David", 29));
            dao.save(new Person("Michelle", 25));

            // fetch all customers
            log.info("Person found with findAll():");
            log.info("-------------------------------");
            for (Person person : dao.findAll()) {
                log.info(person.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            dao.findById(1L)
                    .ifPresent(person -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(person.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            dao.findByNom("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };
    }
}
