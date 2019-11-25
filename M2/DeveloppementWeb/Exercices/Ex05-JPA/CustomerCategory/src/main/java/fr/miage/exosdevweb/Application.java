/**
 *
 */
package fr.miage.exosdevweb;

import fr.miage.exosdevweb.core.Customer;
import fr.miage.exosdevweb.core.Person;
import fr.miage.exosdevweb.dao.CustomerDao;
import fr.miage.exosdevweb.dao.PersonDao;
import fr.miage.exosdevweb.web.controllers.UrlMapping;
//import fr.miage.exosdevweb.repo.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Classe principale pour Spring-boot
 *
 * @author yvan
 *
 * <h1>Exercice 1</h1>
 *
 * <h2>Présentation</h2>
 * <p>
 * Une application qui affiche un messsage, l'heure et la date du genre
 * </p>
 * <p>
 * Bonjour Nous sommes le 2018-10-12. Il est 15:38:27.284
 * </p>
 * lorsqu'on entre l'url /datetime
 *
 * <h2>pom.xml</h2>
 * <p>
 * Il faut créer un projet maven java application et ajouter les dépendances
 * spring-boot-starter-web et éventuellement spring-boot-devtools de chez
 * org.springframework.boot dans le pom.xml
 * </p>
 * <p>
 * Il faut également ajouter le plugin spring-boot-maven-plugin
 * </p>
 * <p>
 * &lt;build&gt;<br>
 * &lt;plugins&gt;<br>
 * &lt;plugin&gt;<br>
 * &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;<br>
 * &lt;artifactId&gt;spring-boot-maven-plugin&lt;/artifactId&gt;<br>
 * &lt;/plugin&gt;<br>
 * &lt;/plugins&gt;<br>
 * &lt;/build&gt;<br>
 * </p>
 *
 *
 * <h2>Classes</h2>
 *
 * <h3>UrlMapping</h3>
 * <p>
 * Dans fr.miage.exosdevweb.web.controllers, créer la classe UrlMapping qui
 * permet de faire le mapping d'url demandé.
 * </p>
 * <p>
 * Il pourra être utile de lire la doc des annotations suivantes :
 * </p>
 * <ul>
 * <li>org.springframework.stereotype.Controller</li>
 * <li>org.springframework.web.bind.annotation.GetMapping</li>
 * <li>org.springframework.web.bind.annotation.ResponseBody</li>
 * </ul>
 * <p>
 * https://docs.spring.io/spring/docs/2.0.5/javadoc-api/
 * </p>
 *
 *
 * <h3>Application</h3>
 *
 * Dans fr.miage.exosdevweb, créer la classe Application qui lance Spring.  * <pre>
public class Application {
 * public static void main(String[] args) {
 * SpringApplication.run(Application.class, args);
 * }
 * }
 * </pre>
 * <p>
 * Il pourra être utile de lire la doc des annotations suivantes :
 * </p>
 * <ul>
 * <li>org.springframework.context.annotation.Configuration</li>
 * <li>org.springframework.boot.autoconfigure.EnableAutoConfiguration</li>
 * <li>org.springframework.context.annotation.ComponentScan</li>
 * </ul>
 *
 * <p>
 * https://docs.spring.io/spring/docs/2.0.5/javadoc-api/
 * </p>
 *
 * <h1>Exercice 2</h1>
 *
 * <h2>Présentation</h2>
 * <p>
 * En s'inspirant de http://spring.io/guides/gs/serving-web-content/, ajouter à
 * l'exercice précédent une url pour que la date et l'heure s'affichent au
 * travers d'un template thymleaf. La date et l'heure sont envoyées au template
 * thymleaf et éventuellement, le nom de la personne à saluer passé en paramètre
 * get (en cas d'absence de ce paramètre, il est remplacé par "cher visiteur"
 * </p>
 * <p>
 * L'ancienne url /datetime doit toujours fonctionnner.
 * </p>
 * <p>
 * Le nouveau comportement doit se faire dans l'url /prettydatetime.
 * </p>
 * <p>
 * ou encore /prettydatetime?name=Yvan.
 * </p>
 *
 * <p>
 * Ajouter la possibilité d'avoir un paramètre age supplémentaire.
 * </p>
 *
 * <p>
 * ou encore /prettydatetime?name=Yvan&amp;age=50.
 * </p>
 * <p>
 * Si le paramètre age est absent ou s'il n'est pas un entier l'age ne s'affiche
 * pas.
 * </p>
 *
 * <h2>pom.xml</h2>
 * <p>
 * Il faut rajouter la dépendance thymleaf dans le pom.xml
 * </p>
 *
 *
 * <h2>UrlMapping</h2>
 * <p>
 * Dans la fr.miage.exosdevweb.web.controllers.UrlMapping, ajouter une méthode
 * et le mapping d'url demandé.
 * </p>
 * <p>
 * Il pourra être utile de lire la doc de :
 * </p>
 *
 * <ul>
 * <li>https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-arguments</li>
 * <li>et celles de l'annotation
 * org.springframework.web.bind.annotation.RequestParam</li>
 * <li>et de la classe org.springframework.ui.Model</li>
 * </ul>
 *
 * <h1>Exercice 3</h1>
 *
 * <h2>Présentation</h2>
 * <p>
 * En s'inspirant de http://spring.io/guides/gs/handling-form- submission/,
 * ajouter à l'exercice précédent une url pour qu'un formulaire demande le nom
 * et l'age d'une personne et qu'elle remplisse les champs d'un objet passé en
 * argument.
 * </p>
 *
 * <p>
 * Les anciennes uris
 * </p>
 * <ul>
 * <li>/datetime</li>
 * <li>/prettydatetime</li>
 * <li>/prettydatetime?name=Toto</li>
 * <li>/prettydatetime?name=Toto&amp;age=25</li>
 * </ul>
 * <p>
 * doivent toujours fonctionnner.
 * </p>
 * <p>
 * La nouvelle uri doit être /inscription.
 * </p>
 * <h2>Classes</h2>
 *
 * <h3>UrlMapping</h3>
 * <p>
 * Il faudra utiliser deux "mappings"
 * </p>
 * <ul>
 * <li>Get pour l'affichage du formulaire. Un objet Person sera envoyé et rempli
 * par le formulaire</li>
 * <li>Post pour le retour du formulaire</li>
 * </ul>
 *
 * <p>
 * Il pourra être utile de lire la doc des annotations suivantes :
 * </p>
 * <ul>
 * <li>org.springframework.web.bind.annotation.ModelAttribute</li>
 * <li>org.springframework.web.bind.annotation.PostMapping</li>
 * </ul>
 * <p>
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
 * </p>
 *
 *
 * <h1>Exercice 4</h1>
 *
 * <h2>Présentation</h2>
 * <p>
 * En s'inspirant de http://spring.io/guides/gs/validating-form-input/, ajouter
 * à l'exercice précédent une url pour que, comme dans l'exercice précédent, un
 * formulaire demande le nom et l'age mais cette fois avec validation.
 * </p>
 *
 *
 * <p>
 * Les anciennes uris doivent toujours fonctionnner.
 * </p>
 * <p>
 * La nouvelle uri doivent être /validation.
 * </p>
 * <h2>pom.xml</h2>
 *
 *
 * <p>
 * Il faut ajouter la dépendance hibernate-validator de chez
 * org.hibernate.validator dans le pom.xml
 * </p>
 *
 * <p>
 * Il pourra être utile de lire la doc des annotations suivantes :
 * </p>
 * <ul>
 * <li>javax.validation.constraints.Min</li>
 * <li>javax.validation.constraints.NotNull</li>
 * <li>javax.validation.constraints.Size</li>
 * <li>javax.validation.Valid</li>
 * </ul>
 * <p>
 * Ainsi que l'interface org.springframework.validation.BindingResult</p>
 * 
 * 
 * <h1>Exercice 5 (JPA)</h1>
 *
 * <h2>Présentation</h2>
 * <p>
 * Réaliser l'exercice http://spring.io/guides/gs/accessing-data-jpa/ dans votre 
 * application pour que la base de données embarquées H2 soit remplie de quelques
 * clients et que les anciennes uris fonctionnent.
 * </p>
 *
 * <h2>pom.xml</h2>
 * <p>
 * Il faut ajouter la dépendance hibernate-validator de chez
 * org.hibernate.validator dans le pom.xml
 * </p>
 *
 * <p>
 * Il pourra être utile de lire la doc des annotations suivantes :
 * </p>
 * <ul>
 * <li>javax.validation.constraints.Min</li>
 * <li>javax.validation.constraints.NotNull</li>
 * <li>javax.validation.constraints.Size</li>
 * <li>javax.validation.Valid</li>
 * </ul>
 * <p>
 * Ainsi que l'interface org.springframework.validation.BindingResult</p>
 *
 */
// Explications 
// @Configuration et @EnableAutoConfiguration sont nécessaires pour lancer
// le serveur tomcat embarqué. Remarque, il écoute sur le port 8080 par défaut
// ou sur le port définit par la paire server.port=7070 du fichier
// src/main/resources/application.properties.
// @Configuration // Inutile si @SpringBootApplication
// @EnableAutoConfiguration//(exclude = {ErrorMvcAutoConfiguration.class})
// @ComponentScan demande à Spring d'aller scanner toutes les classes à partir
// (a priori) du répertoire de la classe Application. Il est possible de 
// préciser le nom du répertoire de scan @ComponentScan("web.controller")
// @ComponentScan // Ou alors :
// @ComponentScan("fr.miage.exosdevweb.web.controllers") // Plus efficace, je suppose. 
// Inutile si @SpringBootApplication
// Ou alors :
// @ComponentScan("fr.miage.exosdevweb") // Si on veut que les répertoires de tous
// les exos soient scannés.

@SpringBootApplication(scanBasePackages = "fr.miage.exosdevweb")
// @SpringBootApplication est un alias pour dire
//    @Configuration
//    @EnableAutoConfiguration
//    @ComponentScan
//    Il rajoute @EnableWebMvc si spring-webmvc est dans le classpath
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    
    @Bean
    public CommandLineRunner demoPerson(PersonDao repository) {
        return (args) -> {
            // save a couple of customers
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
                    .ifPresent(customer -> {
                        LOG.info("Customer found with findById(1L):");
                        LOG.info("--------------------------------");
                        LOG.info(customer.toString());
                        LOG.info("");
                    });

            // fetch customers by last name
            LOG.info("Customer found with findByLastName('Bauer'):");
            LOG.info("--------------------------------------------");
            repository.findByNom("Jack").forEach(bauer -> {
                LOG.info(bauer.toString());
            });
            // fetch customers by last name
            LOG.info("Customer found with findByAge('Bauer'):");
            LOG.info("--------------------------------------------");
            repository.findByAgeBetween(0, 100).forEach(bauer -> {
                LOG.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            LOG.info("");
        };
    }

    @Bean
    public CommandLineRunner demoCustomer(CustomerDao repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            LOG.info("Customers found with findAll():");
            LOG.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                LOG.info(customer.toString());
            }
            LOG.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(customer -> {
                        LOG.info("Customer found with findById(1L):");
                        LOG.info("--------------------------------");
                        LOG.info(customer.toString());
                        LOG.info("");
                    });

            // fetch customers by last name
            LOG.info("Customer found with findByLastName('Bauer'):");
            LOG.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                LOG.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            LOG.info("");
        };
    }
}
