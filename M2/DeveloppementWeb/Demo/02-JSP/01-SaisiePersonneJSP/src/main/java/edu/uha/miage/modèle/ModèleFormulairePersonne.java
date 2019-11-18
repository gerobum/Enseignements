/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uha.miage.modèle;

import edu.uha.miage.metier.Personne;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yvan
 */
public class ModèleFormulairePersonne implements Serializable {
    // Les champs de saisie du formulaire
    private String nom = "", prénom = "", âge = "", genre = "Homme";
    // Pour la validation de l'âge
    private boolean âgeOk;
    // Le message de bienvenue ou d'alerte.
    private String message = "";
    // La personne s'il est possible de la créer.
    private Personne personne;

    public ModèleFormulairePersonne(Personne personne) {
        this.setPersonne(personne);
    }

    public ModèleFormulairePersonne() {
        personne = new Personne();
        message = "";
        âgeOk = true;
    }

    public ModèleFormulairePersonne(String msg) {
        personne = new Personne();
        message = msg;
        âgeOk = true;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
        nom = personne.getNom();
        prénom = personne.getPrenom();
        âge = personne.getAge()+"";
        genre = personne.isMonsieur() ? "Homme" : "Femme";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isÂgeOk() {
        return âgeOk;
    }

    public void setÂgeOk(boolean âgeOk) {
        this.âgeOk = âgeOk;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getÂge() {
        return âge;
    }

    public void setÂge(String âge) {
        this.âge = âge;
    }
       
    public static ModèleFormulairePersonne handle(HttpServletRequest request) {
        // Création d'un modèle de formulaire vierge
        ModèleFormulairePersonne modèle = new ModèleFormulairePersonne();
        // Récupération des champs saisies dans le formulaire
        modèle.nom = (String) request.getParameter("nom");
        modèle.prénom = (String) request.getParameter("prenom");
        modèle.âge = request.getParameter("age");
        modèle.genre = request.getParameter("genre");
        // Vérification de l'âge
        int âge = -1;
        try {
            âge = Integer.parseInt(modèle.âge);
            modèle.âgeOk = âge > 0;
        } catch (NumberFormatException ex) {
            modèle.âgeOk = false;
        }
        // Construction du message et des alertes.
        if (modèle.âgeOk) {
            modèle.personne = new Personne(modèle.genre.equals("Homme"), modèle.nom, modèle.prénom, âge);
            modèle.message = String.format("Bonjour %s %s %s, %d ans", 
                    (modèle.personne.isMonsieur()?"M.":"Mme"), 
                    modèle.personne.getPrenom(), 
                    modèle.personne.getNom(), 
                    modèle.personne.getAge());
        } else {
            modèle.setMessage("L'âge doit être un entier supérieur à 0 écrit en chiffres");
        }
        return modèle;
    }
}
