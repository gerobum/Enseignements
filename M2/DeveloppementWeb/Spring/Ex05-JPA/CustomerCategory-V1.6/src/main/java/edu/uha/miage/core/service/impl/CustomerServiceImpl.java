package edu.uha.miage.core.service.impl;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.entity.Customer;
import edu.uha.miage.core.repository.CategoryRepository;
import edu.uha.miage.core.repository.CustomerRepository;
import edu.uha.miage.core.service.CustomerService;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CategoryRepository categoryRepository;

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
        return (List<Customer>) customerRepository.findAllByOrderByName();
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

    // #### V1.6
    @Override
    public List<Customer> findByCategoryOrderByName(Category category) {
        return customerRepository.findByCategoryOrderByName(category);
    }

    @Override
    public List<Customer> findByCategoryOrderByName(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            return findAll();
        } else {
            if ("0".equals(categoryName)) {
                return customerRepository.findByCategoryOrderByName(null);
            } else {
                Category category = categoryRepository.findByName(categoryName);
                if (category != null) {
                    return customerRepository.findByCategoryOrderByName(category);
                } else {
                    return new LinkedList<>();
                }
            }
        }
    }
}
