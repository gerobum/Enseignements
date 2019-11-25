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

import edu.uha.miage.dao.PersonDao;
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
    
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
    
    
    @Bean
    public CommandLineRunner demoPerson(PersonDao repository) {
        return (args) -> {
            // save some persons
            repository.save(new Person("Joe", 30));
            repository.save(new Person("William", 29));
            repository.save(new Person("Jack", 28));
            repository.save(new Person("Averell", 27));
            repository.save(new Person("Ma", 80));
            repository.save(new Person("Old Timer", 99));
            //repository.save(Person.NOBODY);

            // fetch all customers
            LOG.info("Customers found with findAll():");
            LOG.info("-------------------------------");
            for (Person person : repository.findAll()) {
                LOG.info(person.toString());
            }
            LOG.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(person -> {
                        LOG.info("Customer found with findById(1L):");
                        LOG.info("--------------------------------");
                        LOG.info(person.toString());
                        LOG.info("");
                    });

            // fetch customers by last name
            LOG.info("Customer found with findByLastName('Joe'):");
            LOG.info("--------------------------------------------");
            repository.findByNom("Joe").forEach(joe -> {
                LOG.info(joe.toString());
            });
            LOG.info("");
        };
    }

}
