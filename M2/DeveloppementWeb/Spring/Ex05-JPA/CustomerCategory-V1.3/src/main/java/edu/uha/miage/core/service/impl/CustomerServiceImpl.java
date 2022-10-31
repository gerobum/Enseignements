package edu.uha.miage.core.service.impl;

import edu.uha.miage.core.entity.Customer;
import edu.uha.miage.core.repository.CustomerRepository;
import edu.uha.miage.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer entity) {
        return customerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

// #### V1.3 L'avantage de modifier à l'implémentation de findAll dans la couche
// ######### service est que toute invocation à cette méthode aura pour effet
// ######### de toujours retourner la liste dans l'ordre alphabétique. 
// ######### Il n'y aura rien d'autres à toucher. 
    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAllByOrderById();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByName(String name) {
        return customerRepository.findByName(name);
    }
}
