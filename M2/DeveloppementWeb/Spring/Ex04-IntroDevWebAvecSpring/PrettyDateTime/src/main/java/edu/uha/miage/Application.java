/*
 * Copyright (C) 2019 Yvan Maillot <yvan.maillot@uha.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.uha.miage;

import edu.uha.miage.jpa.PersonRepository;
import edu.uha.miage.metier.Person;
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

    Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
    
    @Bean
  public CommandLineRunner demo(PersonRepository repository) {
    return (args) -> {
      // save a few customers
      repository.save(new Person("Joe", 30));
      repository.save(new Person("William", 29));
      repository.save(new Person("Jack", 28));
      repository.save(new Person("Averell", 27));
      repository.save(new Person("Ma", 80));
      repository.save(new Person("Old Timer", 80));

      // fetch all person
      log.info("Person found with findAll():");
      log.info("-------------------------------");
      for (Person person : repository.findAll()) {
        log.info(person.toString());
      }
      log.info("");

      // fetch an individual customer by ID
      Person person = repository.findById(1L);
      log.info("Person found with findById(1L):");
      log.info("--------------------------------");
      log.info(person.toString());
      log.info("");

      // fetch customers by last name
      log.info("Person found with findByNom('Jack'):");
      log.info("--------------------------------------------");
      repository.findByNom("Jack").forEach(bauer -> {
        log.info(bauer.toString());
      });
      log.info("--------------------------------------------");
      log.info("Person found with findByAge(80):");
      log.info("--------------------------------------------");
       for (Person p : repository.findByAge(80)) {
        log.info(p.toString());
       }
      log.info("--------------------------------------------");
      log.info("");
    };
  }
}
