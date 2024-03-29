/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uha.miage.metier.interfaces.implementations;


import edu.uha.miage.dao.PersonDao;
import edu.uha.miage.metier.Person;
import edu.uha.miage.metier.interfaces.PersonService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Override
    public Person save(Person person) {
        return personDao.save(person);
    }

    @Override
    public void delete(Long id) {
        personDao.deleteById(id);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public Person findById(Long id) {
        return personDao.findById(id).orElse(new Person());
    }

    @Override
    public List<Person> findByNom(String nom) {
        return personDao.findByNom(nom);
    }

    @Override
    public List<Person> findByAge(int age) {
        return personDao.findByAge(age);
    }

    @Override
    public List<Person> findByAgeBetween(int min, int max) {
        return personDao.findByAgeBetween(min, max);
    }

    @Override
    public List<Person> findByNomLikeAndAgeBetween(String nom, int min, int max) {
        return personDao.findByNomLikeAndAgeBetween(nom, min, max);
    }

    @Override
    public List<Person> findAllOrderByAge() {
        return personDao.findByNomLikeOrderByAge("%");
    }
    
    
}
