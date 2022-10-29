package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Customer;
import edu.uha.miage.core.service.CustomerService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// #### V0.0 @Controller indique que la classe qu'elle annote devient un contrôleur web
// ######### This annotation serves as a specialization of @Component, allowing
// ######### for implementation classes to be autodetected through classpath
// ######### scanning. It is typically used in combination with annotated
// ######### handler methods based on the RequestMapping annotation.

// #### V0.0 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html
@Controller
// #### V0.0 Toutes les méthodes de cette classe annotées @RequestMapping ou
// ######### @GetMapping ou @PostMapping seront mappées sur une url basée sur
// ######### /customer
// ######### https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html
@RequestMapping("/customer")
public class CustomerController {


// #### V0.0 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html
// ######### Injection de dépendance (en l'occurrence CustomerServiceImpl)
    @Autowired
    CustomerService customerService;

// #### V0.0 La méthode findAll 
// ######### Comportement exécuté dans le cas d'une requête HTTP/GET sur
// ######### /customer ou  /customer/
    @RequestMapping(method = RequestMethod.GET)
// #### V0.0 @GetMapping({"","/"}) aurait pu être remplacé par 
// ######### @RequestMapping(method = RequestMethod.GET), une affaire de goût.

// #### V0.0 Spring utilise l'injection de dépendance pour exécuter un 
// ######### comportement défini par cette méthode.
// ######### Ce comportement est plutôt une "interprétation" de cette méthode.
// ######### Par exemple, la chaîne qu'elle retourne ne sera pas dans la
// ######### réponse HTTP (sauf si on utilise @ResponseBody). Cette chaîne doit
// ######### correspondre au nom d'un template html qui se trouve dans le dossier
// ######### src/main/resources/templates
// ######### Il s'agit du fichier src/main/resources/templates/customer/list.html
// ######### Par ailleurs, l'emploi du paramètre Model model permet d'envoyer
// ######### des données au template (à la vue). En l'occurrence, il s'agit de
// ######### la liste de tous les clients enregistrés dans la base de données
// ######### qui est envoyée à la vue pour être affichée.
// ######### Le retour et les paramètres de cette fonction pilote le comportement
// ######### de Spring.
// ######### Pour en savoir plus :
// #########  - https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-arguments
// #########  - https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-return-types
    public String findAll(Model model) {
        // #### V0.0  Le but de l'URL /customer est d'afficher la liste des
        // ######### clients existants. La méthode customerService.findAll()
        // ######### interroge la BDD pour retourner cette liste qui est envoyée
        // ######### au template src/main/resources/templates/customer/list.html
        // ######### par l'intermédiaire du modèle dans lequel il est identifié
        // ######### par la clé "customers".
        model.addAttribute("customers", customerService.findAll());
        return "customer/list";
    }

// #### V0.0 La méthode create demande le formulaire de création d'un nouveau
// ######### client. Ce formulaire présente des champs à remplir par
// ######### l'utilisateur. Sa validation provoque le lancement d'une requête
// ######### HTTP/POST (méthode created un peu plus loin).
// ######### Ce comportement est exécuté dans le cas d'une requête HTTP/GET sur
// ######### /customer/create
    @GetMapping("/create")
    public String create(Model model) {
// #### V0.0 Un client (vide) est créé. Il est rare d'utiliser new en
// ######### Spring, sauf dans le cas des entités (comme c'est le cas ici)
        Customer customer = new Customer();
// #### V0.0 Il est envoyé à la "vue" par l'entremise du modèle (model)
// ######### Il sera renseigné dans le formulaire.
        model.addAttribute("customer", customer);
// #### V0.0 Le template src/main/resources/templates/customer/edit.html
        return "customer/edit";
    }

// #### V0.0 La méthode created enregistre le client saisi dans le  
// ######### formulaire (si ses contraintes sont respectées)
// ######### Ce comportement est exécuté dans le cas d'une requête HTTP/POST sur
// ######### /customer/create (engendrée par le formulaire)
    @PostMapping("/create")
    public String created(@Valid Customer customer, BindingResult br) {
// #### V0.0 L'entité Customer (@Entity) possède un champs name qui doit 
// ######### éventuellement respecter des contraintes.
// ######### L'annotation @Valid demande à Spring (ou plutôt Hibernate Validator)
// ######### de vérifier le respect de ces contraintes.
// ######### Le paramètre BindingResult permet de vérifier les éventuels erreurs.
        if (br.hasErrors()) {
// #### V0.0 En cas d'erreur, Le template 
// ######### src/main/resources/templates/customer/edit.html est invoqué
            return "customer/edit";
        }
// #### V0.0 Si tout s'est bien passé, la client est enregistré dans la BDD.
        customerService.save(customer);

// #### V0.0 "redirect:" indique à Spring de faire une redirection, en d'autres
// ######### termes, ça revient à une requête HTTP/GET sur /customer
        return "redirect:/customer";
    }

// #### V0.0 Les deux méthodes suivantes, edit et edited, servent à modifier un
// ######### client. La première (HTTP/GET) invoque le formulaire, la seconde
// ######### (HTTP/POST) réalise la modification s'il n'y a pas d'erreur.
    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id).get());
        return "customer/edit";
    }

    @PostMapping("/edit")
    public String edited(@Valid Customer customer, BindingResult br) {
        if (br.hasErrors()) {
            return "customer/edit";
        }
// #### V0.0 (HTTP/POST) save() peut être utilisé soit
// #########    - pour la création en l'absence d'identifiant (dans ce cas,
// #########      il calculé automatiquement)
// #########    - pour la modification avec un identifiant.
        customerService.save(customer);
        return "redirect:/customer";
    }

// #### V0.0 La méthode delete permet de retirer un client de la BDD
// ######### à partir de par son identifiant.
// ######### Ce comportement est exécuté dans le cas d'une requête HTTP/GET sur
// ######### /customer/delete/{id} où est un identifiant entier (long).
    @GetMapping("/delete/{id}")
// #### V0.0 Une autre façon de passer des arguments. Elle est précisée grâce à
// ######### @PathVariable("id") où "id" correspond à {id}
    public String delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return "redirect:/customer";
    }
}
