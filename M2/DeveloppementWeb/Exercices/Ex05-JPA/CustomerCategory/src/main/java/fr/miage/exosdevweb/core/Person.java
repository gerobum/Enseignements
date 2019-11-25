
package fr.miage.exosdevweb.core;

import java.io.Serializable;
import javax.persistence.Column;
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
    
    public static final Person NOBODY = new Person("Nobody", 999);
    
    @NotNull
    @Size(min=2, max=30)
    @Column
    private String nom;
    
    @NotNull
    @Min(18)
    @Column
    private int age;    
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    public Person() {
        
    }

    public Person(String nom, int age) {
        this.nom = nom;
        this.age = age;
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
        return String.format("%s (%d %s)", nom, age, (age>0?"ans":"an"));
    }
}
