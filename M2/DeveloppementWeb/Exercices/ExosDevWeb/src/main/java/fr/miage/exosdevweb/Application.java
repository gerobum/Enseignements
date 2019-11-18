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

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(PersonDAO dao) {
        return (args) -> {
            // save a couple of person
            dao.save(new Person("Jack", 50));
            dao.save(new Person("Chloe", 48));
            dao.save(new Person("Kim", 30));
            dao.save(new Person("David", 29));
            dao.save(new Person("Michelle", 25));

            // fetch all customers
            LOG.info("Person found with findAll():");
            LOG.info("-------------------------------");
            for (Person person : dao.findAll()) {
                LOG.info(person.toString());
            }
            LOG.info("");

            // fetch an individual customer by ID
            dao.findById(1L)
                    .ifPresent(person -> {
                        LOG.info("Person found with findById(1L):");
                        LOG.info("--------------------------------");
                        LOG.info(person.toString());
                        LOG.info("");
                    });

            // fetch customers by last name
            LOG.info("Person found with findByNom('Chloe'):");
            LOG.info("--------------------------------------------");
            dao.findByNom("Chloe").forEach(bauer -> {
                LOG.info(bauer.toString());
            });
            LOG.info("");
        };
    }
}
