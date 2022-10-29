package edu.uha.miage.core.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// #### V0.0 @Entity pour indiquer que c'est une entité JPA qui sera traduit en table.
// ######### voir https://docs.oracle.com/javaee/7/api/javax/persistence/Entity.html
// ######### voir https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
@Entity
// #### V0.0 En l'absence de nom de table, celle-ci s'appelle Customer (comme la classe)
// ########## Par ailleurs, il est précisé que le champ "name" doit être unique.
// ########## Il y a d'autres manières de le faire. Nous le verrons plus tard.
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Customer implements Serializable {

    // #### V0.0 Pour définir l'identifiant de la table (nécessaire pour faire du JPA).
    @Id
    // #### V0.0 Pour laisser à Spring le soin de générer un identifiant unique.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // #### V0.0 javax.validation.constraints offre la possibilité de demander à
    // ######### Spring de vérifier le respect de certaines contraintes.
    // ######### Par exemple, @NotNull pour préciser que ce champ ne doit pas rester nul
    @NotNull
    // ####  @Size pour préciser que sa taille est entre 2 et 30 caractères.
    @Size(min = 2, max = 30)
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    // #### V0.0 Customer est une entité (@Entity) donc un bean. Un bean doit 
    // ######### posséder un contructeur par défaut, afin que Spring puisse
    // ######### l'instancier.
    public Customer() {
    }

    // #### V0.0 ainsi que des getters et des setters pour tous les attributs.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
