/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package edu.uha.miage.urlmapping;

import edu.uha.miage.urlmapping.controllers.PersonRepository;
import edu.uha.miage.urlmapping.service.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
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
      repository.save(new Person("Joe", 30));
      repository.save(new Person("Jack", 29));
      repository.save(new Person("William", 28));
      repository.save(new Person("Averell", 27));

      // fetch all customers
      log.info("Person found with findAll():");
      log.info("-------------------------------");
      for (Person person : repository.findAll()) {
        log.info(person.toString());
      }
      log.info("");

      // fetch an individual customer by ID
      Person person = repository.findByNom("Joe");
      log.info("Customer found with findByNom(\"Joe\"):");
      log.info("--------------------------------");
      log.info(person.toString());
      log.info("");
    };
  }
}
