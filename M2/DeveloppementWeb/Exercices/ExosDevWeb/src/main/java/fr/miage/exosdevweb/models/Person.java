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
@Entity // 5.1
public class Person implements Serializable {
    @NotNull // 4
    @NotNull(message = "Le nom est nécessaire")
    @Size(min = 2, message = "Le nom doit contenir au moins deux lettres") // 4
    private String nom;
    @NotNull(message = "L'âge est nécessaire") // 4
    @Min(value = 18, message = "Vous devez être majeur") // 4
    private Integer age;
    
    @Id // 5.1
    @GeneratedValue // 5.1
    private Long id;

    public Person() { // 5.1
        this("", null);
    }

    public Person(String nom, Integer age) {
        this.nom = nom;
        this.age = age;
    }

    public String getNom() { // 5.1
        return nom;
    }

    public void setNom(String nom) { // 5.1
        this.nom = nom;
    }

    public Integer getAge() { // 5.1
        return age;
    }

    public void setAge(Integer age) { // 5.1
        this.age = age;
    }
    
    @Override // 5.1
    public String toString() {
        return nom + "("+age+" ans)";
    }
    
    
}
