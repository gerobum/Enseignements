package edu.uha.miage.core.service;

import java.util.List;
import java.util.Optional;

import edu.uha.miage.core.entity.Customer;
// #### V0.0 Couche service
// ######### C'est une couche intermédiaire

// #### V0.0 On y définit les méthodes dont on a besoin.
// ######### Cette interface nécessite une implémentation, mais elle est très
// ######### simple à faire. Voir le paquetage impl
public interface CustomerService {
    Customer save(Customer entity);
    void delete(Long id);
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    Customer findByName(String name);
    Customer getOne(Long id);
}
