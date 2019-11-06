package fr.miage.core.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Ancien implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //@NotNull
    //@Size(min = 2, max = 30)
    private String nom;
    
    //@NotNull
    //@Size(min = 2, max = 30)
    private String prenom;
    
    //@Min(1970)
    private int promo;

    public Ancien(String nom, String prenom, int promo) {
        this.nom = nom;
        this.prenom = prenom;
        this.promo = promo;
    }
    
    public Ancien() {
        this("", "", 2019);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getPromo() {
        return promo;
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }
    
    @Override
    public String toString() {
        return prenom + " " + nom + " (promo " + promo + ")";
    }

}
