// #### V2.1 
// Création de deux profils :
// 1. Un profil « dev », dédié au développement, pourvu d’une BDD H2 (en mémoire) 
//    et qui réalise des tests unitaires
// 2. Un profil « prod », dédié à la production, pourvu d’une BDD mysql et qui 
//    ne s'encombre pas des dépendances liées à JUnit

package edu.uha.miage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.uha.miage")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
