/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.exosdevweb.core.interfaces;

import fr.miage.exosdevweb.core.Customer;
import java.util.List;

/**
 *
 * @author yvan
 */

public interface CustomerService {

    Customer save(Customer entity);

    void delete(Long id);

    List<Customer> findAll();

    Customer findById(Long id);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByFirstName(String firstName);

}
