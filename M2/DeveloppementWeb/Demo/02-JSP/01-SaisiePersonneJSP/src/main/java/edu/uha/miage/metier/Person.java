
package edu.uha.miage.metier;

import java.io.Serializable;

/**
 *
 * @author yvan
 */
public class Person implements Serializable {
    private boolean mister;
    private String name, surname;
    private int age;

    public Person() {
        this(true, "", "", 0);
    }
    
    public Person(boolean mister, String name, String surname, int age) {
        this.mister = mister;
        this.name = surname;
        this.surname = name;
        this.age = age;
    }
    public boolean isMister() {
        return mister;
    }
    public String getSurname() {
        return surname;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    } 

    public void setMister(boolean mister) {
        this.mister = mister;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }        
}
