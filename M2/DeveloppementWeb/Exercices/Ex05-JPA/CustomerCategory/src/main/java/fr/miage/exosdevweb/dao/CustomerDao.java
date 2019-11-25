/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.exosdevweb.dao;

import fr.miage.exosdevweb.core.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yvan
 */
public interface CustomerDao extends JpaRepository<Customer, Long> {
    public List<Customer> findByLastName(String lastName);
    public List<Customer> findByfirstName(String lastName);
}
