/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.exosdevweb.dao;

import fr.miage.exosdevweb.core.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author yvan
 */
public interface PersonDao extends JpaRepository<Person, Long> {
    List<Person> findByNom(String nom);
    List<Person> findByAge(int age);
    List<Person> findByAgeBetween(int min, int max);
    List<Person> findByNomLikeAndAgeBetween(String nom, int min, int max);
}
