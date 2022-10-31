// #### V1.6 
// Ajouter la possibilité de faire l'affichage de la liste de tous les clients 
// d'une certaine categorie.
// 
// Il faut autoriser les urls /customer/{x}, où {x} est un nom de catégorie, 
// comme A par exemple.
//  
//  Le template list.html est utiliser dans tous les cas.
//
//  Quelques exemples
//
//  /customer ou /customer => affichage de la liste de tous les clients (comme avant)
//
//  /customer/A => affichage de la liste de tous les clients de catégorie A (si elle existe)
//
//  De plus le titre de la liste doit être contextuel
// 
//  Par exemple, liste des clients de catégorie A (penser à l'i18n)
// 
//  /customer/Y => message indiquant l'absence de cette catégorie 
//  (si elle n'existe pas)
//
//  /customer/WithoutCategory => liste des clients sans category
package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
