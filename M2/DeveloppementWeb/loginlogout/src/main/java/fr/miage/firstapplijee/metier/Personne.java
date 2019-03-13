/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.firstapplijee.metier;

import java.io.Serializable;

/**
 *
 * @author yvan
 */
public class Personne implements Serializable {
    private boolean monsieur;
    private String nom, prenom;
    private int age;

    public Personne() {
        this(true, "Personne", "Juste", 0);
    }
    
    public Personne(boolean monsieur, String nom, String prenom, int age) {
        this.monsieur = monsieur;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }
    public boolean isMonsieur() {
        return monsieur;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public int getAge() {
        return age;
    } 

    public void setMonsieur(boolean monsieur) {
        this.monsieur = monsieur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }        
}
