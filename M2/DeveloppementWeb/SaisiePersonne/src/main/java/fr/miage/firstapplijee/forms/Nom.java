/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.firstapplijee.forms;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yvan
 */
public class Nom implements Serializable{
    private String nom;

    public Nom() {
        this.nom = "****";
    }

    public Nom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void handle(HttpServletRequest request) {
        this.nom = request.getParameter("nom");
        request.setAttribute("form", this);
    }
}
