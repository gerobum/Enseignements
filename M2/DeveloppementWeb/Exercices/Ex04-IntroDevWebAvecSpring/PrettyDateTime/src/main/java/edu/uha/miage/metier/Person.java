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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */

public class Person implements Serializable {
    
    @NotNull
    @Size(min = 2)
    private String nom;
    @NotNull
    @Min(18)
    private Integer age;

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
                (nom==null || nom.isBlank()?"nobody" : nom), 
                (age < 0 ? "" : (age > 2 ? String.format("(%d ans)", age) :  String.format("(%d an)", age))));
    }
    
}
