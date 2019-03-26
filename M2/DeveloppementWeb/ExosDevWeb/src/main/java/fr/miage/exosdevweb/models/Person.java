/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.exosdevweb.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yvan
 */
@Entity
public class Person implements Serializable {
    @NotNull // 4
    @Size(min = 2) // 4
    @Column
    private String nom;
    @NotNull // 4
    @Min(18) // 4
    @Column
    private Integer age;
    
    @Id
    @GeneratedValue
    @Column
    private Long id;

    public Person() {
        this("", null);
    }

    public Person(String nom, Integer age) {
        this.nom = nom;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    
}
