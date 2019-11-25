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
package edu.uha.miage.models;

import edu.uha.miage.metier.Person;
import java.io.Serializable;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class PersonModel implements Serializable {

    private String nom;
    private String age;
    private Person person;

    private void newPerson() {
        try {
            person = new Person(nom, Integer.parseInt(age));
        } catch (Exception ex) {
            person = new Person();
        }
    }

    public PersonModel(String nom, String age) {
        this.nom = nom;
        this.age = age;
        newPerson();
    }

    public PersonModel() {
        this("", "");
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        newPerson();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        newPerson();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    

}
