package edu.uha.miage.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Pattern(regexp = "[A-Z]")
    private String name;
    
    // #### V1.4 Une catégorie concerne plusieurs clients @OneToMany
    // ######### C'est une relation bidirectionnelle. 
    // ######### Le paramètre (mappedBy = "category") indique que Category est 
    // ######### la table esclave. C'est-à-dire que c'est la table Customer qui
    // ######### contient une clé étrangère "id_catégorie".
    @OneToMany(mappedBy = "category")
    private List<Customer> customers;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

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

    // #### V1.4 On peut retourner la liste des clients de cette catégorie
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
