/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sansTest;


import java.util.GregorianCalendar;

public enum Mois {

    JANVIER("Janvier", 31),
    FEVRIER("Février", 28, true),
    MARS("Mars", 31),
    AVRIL("Avril", 30),
    MAI("Mai", 31),
    JUIN("Juin", 30),
    JUILLET("Juillet", 31),
    AOUT("Août", 31),
    SEPTEMBRE("Septembre", 30),
    OCTOBRE("Octobre", 31),
    NOVEMBRE("Novembre", 30),
    DECEMBRE("Décembre", 31);

    private final int nbJours;
    public final String nom;

    Mois(String nom, int n, boolean fevrier) {
        this.nom = nom;
        if (fevrier) {
            GregorianCalendar gc = new GregorianCalendar();
            int aec = gc.get(GregorianCalendar.YEAR);
            if (gc.isLeapYear(aec)) {
                this.nbJours = 29;
            } else {
                this.nbJours = 28;
            }
        } else {
            this.nbJours = n;
        }
    }
    
    Mois(String nom, int n) {
        this(nom, n, false);
    }

    public int nbJours() {
        return nbJours;
    }

    @Override
    public String toString() {
        return nom;
    }

    public int numéro() {
        return ordinal() + 1;
    }
}
