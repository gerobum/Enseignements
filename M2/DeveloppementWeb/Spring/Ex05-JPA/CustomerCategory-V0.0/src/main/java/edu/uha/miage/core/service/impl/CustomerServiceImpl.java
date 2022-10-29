package edu.uha.miage.core.service.impl;

import edu.uha.miage.core.entity.Customer;
import edu.uha.miage.core.repository.CustomerRepository;
import edu.uha.miage.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// #### V0.0 @Component indique une classe candidate à l'auto-détection (@Autowired)
// ######### https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Component.html
@Component
public class CustomerServiceImpl implements CustomerService {

// #### V0.0 @Autowired permet d'injecter automatiquement la bonne dépendance.
// ######### Elle est fabriquée automatiquement par Spring (voir CustomerRepository)
    @Autowired
    CustomerRepository customerRepository;

// #### V0.0 Le reste s'écrit facilement.
    @Override
    public Customer save(Customer entity) {
        return customerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public Customer getOne(Long id) {
        return customerRepository.getOne(id);
    }
}
