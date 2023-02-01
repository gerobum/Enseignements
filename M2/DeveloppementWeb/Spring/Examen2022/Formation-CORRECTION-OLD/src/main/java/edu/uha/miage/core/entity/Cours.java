package edu.uha.miage.core.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
public class Cours implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;
    @Positive
    private int nbHeuresCours;

    @ManyToMany
    private List<Enseignant> enseignants;
    @ManyToOne
    @NotNull
    private Enseignant responsable;

    public Cours(String title, int nbHeuresCours, Enseignant responsable) {
        this.title = title;
        this.nbHeuresCours = nbHeuresCours;
        this.responsable = responsable;
    }

    public Cours() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public Enseignant getResponsable() {
        return responsable;
    }

    public void setResponsable(Enseignant responsable) {
        this.responsable = responsable;
    }

    public String toString() {
        return title + ", Responsable : " + responsable + " (" + nbHeuresCours + " h)";
    }

    public int getNbHeuresCours() {
        return nbHeuresCours;
    }

    public void setNbHeuresCours(int nbHeuresCours) {
        this.nbHeuresCours = nbHeuresCours;
    }
}
