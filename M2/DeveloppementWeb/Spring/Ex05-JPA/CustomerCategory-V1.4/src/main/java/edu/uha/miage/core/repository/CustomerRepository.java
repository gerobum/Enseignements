package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
    
    List<Customer> findAllByOrderByName();
}
