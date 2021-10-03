package edu.uha.miage.exosdevweb;

import edu.uha.miage.exosdevweb.dao.PersonRepository;
import edu.uha.miage.exosdevweb.core.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);

    }

    @Bean
    public CommandLineRunner demo(PersonRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Person("Luke", 26));
            repository.save(new Person("Jo", 30));
            repository.save(new Person("Jack", 29));
            repository.save(new Person("William", 28));
            repository.save(new Person("Averell", 27));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Person person : repository.findAll()) {
                log.info(person.toString());
            }
        };
    }
}
