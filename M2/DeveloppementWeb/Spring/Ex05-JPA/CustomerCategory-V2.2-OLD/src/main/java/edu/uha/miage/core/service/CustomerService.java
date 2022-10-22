package edu.uha.miage.core.service;

import edu.uha.miage.core.entity.Category;
import java.util.List;
import java.util.Optional;

import edu.uha.miage.core.entity.Customer;

public interface CustomerService {
    Customer save(Customer entity);
    void delete(Long id);
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    Customer findByName(String name);
    Customer getOne(Long id); 
    // #### V2.0
    List<Customer> findByCategoryOrderByName(Category category);
    List<Customer> findByCategoryOrderByName(String categoryName);
}
