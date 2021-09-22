package edu.uha.miage.core.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Groupe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    @ManyToMany
    private Set<Etudiant> etudiant;

    public Groupe(String name) {
        this.name = name;
    }
    public Groupe() {
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

    public Set<Etudiant> getStudent() {
        return etudiant;
    }

    public void setStudent(Set<Etudiant> etudiant) {
        this.etudiant = etudiant;
    }

}
