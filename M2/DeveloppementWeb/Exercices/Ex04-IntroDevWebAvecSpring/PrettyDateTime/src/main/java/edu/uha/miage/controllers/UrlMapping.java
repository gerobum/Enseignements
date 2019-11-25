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
package edu.uha.miage.controllers;

import edu.uha.miage.metier.Person;
import edu.uha.miage.models.PersonModel;
import edu.uha.miage.models.PrettyDateTimeModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classe principale pour Spring-boot
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
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

@Controller
public class UrlMapping {

    Logger logger = LoggerFactory.getLogger(UrlMapping.class);

    @GetMapping("/datetime")
    @ResponseBody
    public String datetime() {

        logger.info("Requête GET sur /datetime");

        return "<h1>Bonjour</h1>"
                + "<p>Nous sommes le " + LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")) + "</p>"
                + "<p>Il est " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")) + "</p>";
    }

    @GetMapping({"/prettydatetime", "/pdt"})
    // Aller voir https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-arguments
    public String prettydatetime(
            /*@RequestParam(name = "nom", required = false, defaultValue = "cher visiteur")*/ String name,
            /*@RequestParam(name = "age", required = false, defaultValue = "-1") */ String age,
            Model model) {
        
        logger.info("Requête GET sur /prettydatetime");
        
        model.addAttribute("model", new PrettyDateTimeModel(name, age));
        return "pdtView";
    }

    @GetMapping({"/inscription-sans-precaution"})
    public String inscriptionSansPrecaution(Model model) {
        
        logger.info("Requête GET sur /inscription-sans-precaution");
        
        model.addAttribute("person", new Person());
        return "inscriptionSansPrecautionView";
    }

    @PostMapping({"/inscription-sans-precaution"})
    // Aller voir https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-arguments
    public String inscritSansPrecaution(/* @ModelAttribute */ Person person) {
        
        logger.info("Requête POST sur /inscription-sans-precaution");
        
        logger.info("Inscription de la personne " + person);
     
        return "resultSansPrecautionView";
    }

    @GetMapping({"/inscription"})
    public String inscription(Model model) {
        
        logger.info("Requête GET sur /inscription");
        
        model.addAttribute("personModel", new PersonModel());
        return "inscriptionView";
    }

    @PostMapping({"/inscription"})
    // Aller voir https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-arguments
    public String inscrit(/* @ModelAttribute */ PersonModel personModel, Model model) {
        
        logger.info("Requête POST sur /inscription");
        
        logger.info("Inscription de la personne " + personModel.getPerson());
     
        model.addAttribute("model", new PrettyDateTimeModel(personModel.getPerson().getNom(), personModel.getPerson().getAge()+""));
        return "pdtView";
    }

    @GetMapping({"/validation"})
    public String inscriptionAvecValidation(Model model) {
        
        logger.info("Requête GET sur /validation");    
        
        model.addAttribute("person", new Person());
        return "inscriptionAvecValidationView";

    }
    
    @PostMapping({"/validation"})
    public String inscritAvecValidation(@Valid Person person, BindingResult bindingResult) {
        
        logger.info("Requête POST sur /validation");
        
        if (bindingResult.hasErrors()) {
            return "inscriptionAvecValidationView";
        }
        
        logger.info("Inscription de la personne " + person);
     
        return "bravo";
    }
    
    
    @GetMapping("/")
    public String accueil() {
        return "accueil";
    }
    
    // La partie JPA
    
}
