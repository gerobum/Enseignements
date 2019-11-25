
package fr.miage.exosdevweb.core.interfaces.implementations;

import fr.miage.exosdevweb.core.Customer;
import fr.miage.exosdevweb.core.interfaces.CustomerService;
import fr.miage.exosdevweb.dao.CustomerDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yvan
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer save(Customer entity) {
        return customerDao.save(entity);
    }

    @Override
    public void delete(Long id) {
        customerDao.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerDao.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerDao.findById(id).get();
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return customerDao.findByLastName(lastName);
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return customerDao.findByfirstName(firstName);
    }

    
}
