package edu.uha.miage.urlmapping.services;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

    @NotNull(message = "Le nom est nécessaire")
    @Size(min = 2, message = "Le nom doit contenir au moins deux lettres") // 4
    private String name;
    @NotNull(message = "L'âge est nécessaire") // 4
    @Min(value = 18, message = "Vous devez être majeur") // 4
    private Integer age;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
        this("", null);
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " (" + age + " ans)";
    }

}
