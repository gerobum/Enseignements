/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.firstapplijee.forms;

import fr.miage.firstapplijee.metier.Personne;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yvan
 */
public class PersonForm implements Serializable {

    private Personne personne;
    private String message;
    private boolean ageOk;

    public PersonForm(Personne personne) {
        this.personne = personne;
    }

    public PersonForm() {
        personne = new Personne();
        message = "";
        ageOk = true;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAgeOk() {
        return ageOk;
    }

    public void setAgeOk(boolean ageOk) {
        this.ageOk = ageOk;
    }
    
    

    public static void handle(HttpServletRequest request) {
        PersonForm pf = new PersonForm();
        

        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        int age;
        try {
            age = Integer.parseInt((String) request.getParameter("age"));
            if (age > 0) {
                pf.setAgeOk(true);
            } else {
                pf.setAgeOk(false);
                age = 0;
            }

        } catch (NumberFormatException ex) {
            age = 0;
            pf.setAgeOk(false);
        }
        boolean homme = request.getParameter("genre").equals("Homme");
        Personne personne = new Personne(homme, nom, prenom, age);
        pf.setPersonne(personne);
        if (pf.isAgeOk()) {
            pf.setMessage(String.format("%s %s %s, %d ans", (personne.isMonsieur()?"M.":"Mme"), personne.getPrenom(), personne.getNom(), personne.getAge()));
        } else {
            pf.setMessage("L'âge doit être un entier supérieur à 0");
        }
        request.setAttribute("pf", pf);
    }
}
