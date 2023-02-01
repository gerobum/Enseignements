package edu.uha.miage.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Enseignant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]*")
    private String firstname;
    @NotNull
    @Pattern(regexp = "[A-Z]+")
    private String lastname;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isMiss() {
        return miss;
    }

    public void setMiss(boolean miss) {
        this.miss = miss;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public List<Cours> getResponsabilites() {
        return responsabilites;
    }

    public void setResponsabilites(List<Cours> responsabilites) {
        this.responsabilites = responsabilites;
    }

    private boolean miss;
    
    @ManyToMany
    private List<Cours> cours;
    @OneToMany
    private List<Cours> responsabilites;

    public Enseignant(String firstname, String lastname, boolean miss) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.miss = miss;
    }

    public Enseignant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }

    @Override
    public String toString() {
        return (miss ?"Mme ":"M. ") + firstname + " " + lastname;
    }
}
