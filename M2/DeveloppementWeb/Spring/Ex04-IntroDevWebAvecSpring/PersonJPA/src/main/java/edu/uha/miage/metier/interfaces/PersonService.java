/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uha.miage.metier.interfaces;

import edu.uha.miage.metier.Person;
import java.util.List;

/**
 *
 * @author yvan
 */
public interface PersonService {
    
    Person save(Person entity);

    void delete(Long id);

    List<Person> findAll();

    Person findById(Long id);

    List<Person> findByNom(String nom);

    List<Person> findByAge(int age);

    List<Person> findByAgeBetween(int min, int max);

    List<Person> findByNomLikeAndAgeBetween(String nom, int min, int max);
    
    List<Person> findAllOrderByAge();

}
