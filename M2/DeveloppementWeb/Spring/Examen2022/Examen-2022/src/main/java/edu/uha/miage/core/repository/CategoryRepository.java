package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
// #### V1.0 Couche repository
        
//           (HTTP/POST) Pour rendre persistant l'entité Category en utilisant
//            Hibernate à travers JPA, il suffit, pour l'essentiel, de déclarer    
//            une interface qui hérite de JpaRepository<Category, Long>   
//            dont le 1er paramètre générique est le type à "persister" (Customer)  
//            et le 2eme est le type de son identifiant.

//            L'interface JpaRepository possède déjà un certain nombres de 
//            méthodes pour faire des requêtes dans la table, comme par exemple
//              - findAll() 
//              - deleteById(ID id)
//              - save(S entity)
//              - ...
//            https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html

public interface CategoryRepository extends JpaRepository<Category, Long> {
// #### V1.0 Mais en plus il est possible de composer sa propre méthode
//           Par exemple, findByName parce que le champ name existe dans Customer.
    Category findByName(String name);
// #### V1.0 Il est possible d'imaginer des méthodes plus compliquées comme
//           par exemple findByNameAndSurname(String name, String surname);
//           pour peu que surname soit un champ.    
//           https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
//           Et le plus important et qu'il n'est même pas nécessaire d'écrire 
//           l'implémentation de cette interface. Spring s'en charge ! 
}
