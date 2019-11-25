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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class PrettyDateTimeModel implements Serializable {

    private String name;
    private String ageAsString;
    private int age;
    private String prettyNameAge;
    private String prettyDate;
    private String prettyTime;
    private Person someone;
    
    public PrettyDateTimeModel(String name, String ageAsString) {
        this.name = name;
        this.ageAsString = ageAsString;
        if (name == null || name.isEmpty() || name.isBlank()) {
            this.name = "cher visiteur";
        } else {
            try {
                age = Integer.parseInt(ageAsString);
            } catch (Exception ex) {
                age = -1;
            }
        }
        this.prettyNameAge = this.name;
        if (age > -1) {
            this.prettyNameAge += String.format(" (%d %s)", age, (age > 1 ? "ans" : "an"));
        }
        this.prettyDate = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")); //LocalDate.now().format(dd);
        this.prettyTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")); // LocalDate.now().format(dt);
        this.someone = new Person();
    }

    public PrettyDateTimeModel() {
        this(null, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgeAsString() {
        return ageAsString;
    }

    public void setAgeAsString(String ageAsString) {
        this.ageAsString = ageAsString;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPrettyNameAge() {
        return prettyNameAge;
    }

    public void setPrettyNameAge(String prettyNameAge) {
        this.prettyNameAge = prettyNameAge;
    }

    public String getPrettyDate() {
        return prettyDate;
    }

    public void setPrettyDate(String prettyDate) {
        this.prettyDate = prettyDate;
    }

    public String getPrettyTime() {
        return prettyTime;
    }

    public void setPrettyTime(String prettyTime) {
        this.prettyTime = prettyTime;
    }
    
    public Person getSomeone() {
        return someone;
    }

    public void setSomeone(Person someone) {
        this.someone = someone;
    }
}
