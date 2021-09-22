/*
 * Copyright (C) 2019 Yvan Maillot <yvan.maillot@uha.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.uha.miage.metier;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
@Entity
public class Person implements Serializable {

    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-z\\s-]+", message = "Un mot d\'au moins deux lettres commençant par une majuscule")
    private String nom;
    @NotNull
    @Min(value = 18, message = "Il faut avoir 18 ans ou plus pour s'inscrire")
    private Integer age;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Person(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public Person(String nom) {
        this(nom, -1);
    }

    public Person() {
        this("");
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s %s",
                (nom == null || nom.isBlank() ? "nobody" : nom),
                (age < 0 ? "" : (age > 1 ? String.format("(%d ans)", age) : String.format("(%d an)", age))));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
