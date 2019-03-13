/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.introspring.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author yvan
 */
public class PrettyDateTimeModel implements Serializable {
    private String nom;
    private String date;
    private String time;

    public PrettyDateTimeModel() {
        this("a vous");
    }
    
    public PrettyDateTimeModel(String nom) {
        this.nom = nom;
        this.date = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu"));
        this.time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"));
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
