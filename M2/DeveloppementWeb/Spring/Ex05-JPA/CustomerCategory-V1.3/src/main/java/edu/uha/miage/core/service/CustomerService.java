package edu.uha.miage.core.service;

import java.util.List;
import java.util.Optional;

import edu.uha.miage.core.entity.Customer;

public interface CustomerService {
    Customer save(Customer entity);
    void delete(Long id);
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    Customer findByName(String name);
}
