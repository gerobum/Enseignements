package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByName(String name);  
    List<Customer> findAllByOrderByName(); 
    // #### V1.6
    List<Customer> findByCategoryOrderByName(Category category);
}
