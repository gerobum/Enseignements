/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.exosdevweb.core;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.stereotype.Component;



/**
 *
 * @author yvan
 */
@Component
public class FilterForm {
    private String nom;
    @Min(0)
    private int ageMin = 0;
    @Max(999)
    private int ageMax = 999;

    public FilterForm() {
    }

    /*public FilterForm(String nom) {
        this.nom = nom;
    }*/

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom.trim().isEmpty() || "*".equals(nom.trim()) ? "%" : nom;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    @Override
    public String toString() {
        return "FilterForm{" + "nom=" + nom + ", ageMin=" + ageMin + ", ageMax=" + ageMax + '}';
    }
    
}
