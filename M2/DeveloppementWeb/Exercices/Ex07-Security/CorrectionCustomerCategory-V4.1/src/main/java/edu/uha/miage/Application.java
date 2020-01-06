// #### V0.0 "Customers" application that allows you to list, add and modify
// #### V0.0 customers in a database.
//
// #### V0.1 Internationalization
//
// #### V0.2 The effects of the changes for internationalization are only visible
// #### V0.2 if the location is realyy changed, for example by changing firefox 
// #### V0.2 preferences.
// #### V0.2 In this version, a menu is added on all pages to allow the 
// #### V0.2 change of location using simple buttons.
//
// #### V0.2 On en profitera pour ajouter des titres, en-tête et pied de page
// #### V0.2 sur toutes les pages.
//
// #### V0.3 Utilisation de Bootstrap et js pour avoir une jolie interface.
// #### V0.3 - Les fichiers js et Boostrap sont dans src/main/resources/static
// #### V0.3 - Le dossier src/main/resources/static est déclaré ressources dans SpringWebConfig 
// #### V0.3 - Les templates .html sont agrémentés de décorations bootstap
//
// #### V1.0 Rajout d'une entité Category à gérer comme c'est fait pour les clients.
// #### V1.0 C'est-à-dire la possibilité de créer, de modifier et de supprimer
// #### V1.0 des catégories. La seule différente est que le nom d'une catégorie
// #### V1.0 n'a qu'une et une seule lettre.
// #### V1.0 Ajout d'un menu visible sur toutes les pages qui permet de choisir
// #### V1.0 entre la page qui affiche les clients et celle qui affiche les 
// #### V1.0 catégories, à l'instar de celui qui permet de basculer entre 
// #### V1.0 "anglais" et "français". 
//
// #### V1.1 Remplissage de quelques clients et quelques catégories de démo
// #### V1.1 dans la BDD au démarrage de l'application. Et utilisation d'un  
// #### V1.1 "logger" pour afficher des messages.
//
// #### V1.2 Ajout de quelques tests unitaires.
// #### V1.2 Les modifications se voient "pom.xml" et surtout dans "Test Packages".
// #### V1.2 https://docs.spring.io/spring-batch/3.0.x/reference/html/testing.html
//
// #### V1.3 La liste des clients comme des catégories doit s'afficher dans l'ordre alphabétique.
// #### V1.3 C'est seulement le findAll de la couche service qui sera modifié.
//
// #### V1.4 Un client appartient à 0 ou 1 catégorie, une catégorie est associée à 0, 1
// #### V1.4 ou plusieurs clients. 
// #### V1.4 Le formulaire de création et de modification de client offre maintenant
// #### V1.4 la possibilité d'affecter une catégorie à une client ou de la changer.
//
// #### V1.5 Chaque client s'affiche avec sa catégorie, si elle existe.
// #### V1.5 La seule chose à faire est de redéfinir la méthode toString()
// #### V1.5 de Customer.
//   
// #### V2.0 La liste de tous les clients d'une catégorie donnée s'affiche
// #### V2.0 Par exemple /customer/X affiche les clients de catégorie X.
// #### V2.0 Si X n'existe pas, la liste est vide.
//
// #### V2.1 Création de deux profils : 
// #### V2.1    1. Un profil "dev", dédié au développement, pourvu d'une BDD H2 
// #### V2.1       (en mémoire) et qui réalise des tests unitaires
// #### V2.1    2. Un profil "prod", dédié à la production, pourvu d'une BDD  
// #### V2.1       mysql et qui ne s'encombre pas des dépendances liées à JUnit 
// #### V2.1
// #### V2.1 Remarque : le changement ne se fait pas à chaud. Il faut faire un
// #### V2.1 build pour le valider.

// #### V3.0 Using sessions for a personalized display of visited pages. 

// #### V3.1 Use of spring-sessions (pages are stored in database) 

// #### V3.2 Ajout d'une page error.html 

// #### V3.3 Interception des exceptions d'intégrité de la base de données 

// #### V4.0 Spring security 
// #### V4.0 Utilisation de Spring Security pour 
// #### V4.0    1. Définir les utilisateurs user et admin (dans une base de données)
// #### V4.0       et leur mot de passe.
// #### V4.0    2. Définir des rôles USER et ADMIN
// #### V4.0    3. / et /list sont accessibles à tous
// #### V4.0    4. /customer est accessible à USER et ADMIN (login nécessaire)
// #### V4.0    5. /category est accessible à ADMIN seulement (login nécessaire)

// #### V4.1 Spring security again : add a page to register users
package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// #### V0.0 @SpringBootApplication est un raccourci pour 
// #### V0.0 @EnableAutoConfiguration
// #### V0.0 @ComponentScan
// #### V0.0 @Configuration
// #### V0.0 https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-using-springbootapplication-annotation.html
@SpringBootApplication(scanBasePackages = "edu.uha.miage")
// #### V0.0 "edu.uha.miage" précise à partir d'où commence le scan des beans.


// #### V4.0 Lire https://docs.spring.io/spring-security/site/docs/current/reference/html/index.html
public class Application extends SpringBootServletInitializer implements WebMvcConfigurer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
