package edu.uha.miage.datetime;

import edu.uha.miage.datetime.data.dao.PersonRepository;
import edu.uha.miage.datetime.data.services.PersonService;
import edu.uha.miage.datetime.services.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DateTimeApplication {

	Logger logger = LoggerFactory.getLogger(DateTimeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DateTimeApplication.class, args);
	}

	// Demande à être lancer automatiquement au démarrage de l'application
	@Bean
	public CommandLineRunner demo(PersonRepository repository) {
		return (args) -> {
			// Enregistrement de quelques personnes
			repository.save(new Person("Averell", 27));
			repository.save(new Person("Jack", 28));
			repository.save(new Person("William", 29));
			repository.save(new Person("Joe", 30));
			repository.save(new Person("Ma", 55));
			repository.save(new Person("Old Timer", 99));

			try {
				repository.save(new Person("Billy the kid", 13));
			} catch (Exception ex) {
				logger.info("Impossible d'enregistrer une personne de moins de 18 ans");
			}
		};
	}
}
