package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
    
    // #### V1.3 Notamment, voici comment rédiger l'en-tête d'une méthode pour  
    // ######### que son implémentation auto-générée retourne la liste de toutes 
    // ######### les catégories triée dans l'ordre alphabétique de leur id.   
    List<Customer> findAllByOrderById();    
    // #### V1.3 Notamment, voici comment rédiger l'en-tête d'une méthode pour  
    // ######### que son implémentation auto-générée retourne la liste de toutes 
    // ######### les catégories triée dans l'ordre alphabétique de leur nom.
    List<Customer> findAllByOrderByName();
}
