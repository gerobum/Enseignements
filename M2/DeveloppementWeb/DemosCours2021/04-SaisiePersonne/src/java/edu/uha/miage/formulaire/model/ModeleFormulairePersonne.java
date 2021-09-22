/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package edu.uha.miage.formulaire.model;

import edu.uha.miage.person.service.Personne;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class ModeleFormulairePersonne implements Serializable {
    // Les champs de saisie du formulaire

    private String nom, prenom, age, genre;
    // Pour la validation de l’âge
    private boolean ageOk;
    // Le message de bienvenue ou d’alerte.
    private String message;
    // La personne s’il est possible de la créer.
    private Personne personne;

    public ModeleFormulairePersonne() {
        message = nom = prenom = age = genre = "";
        ageOk = true;
        personne = null;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAgeOk() {
        return ageOk;
    }

    public void setAgeOk(boolean ageOk) {
        this.ageOk = ageOk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public static void handle(HttpServletRequest request) {

        // Création d’un modèle de formulaire vierge
        ModeleFormulairePersonne modele = new ModeleFormulairePersonne();
        // Récupération des champs saisies dans le formulaire
        modele.nom = (String) request.getParameter("nom");
        modele.prenom = (String) request.getParameter("prenom");
        modele.age = request.getParameter("age");
        modele.genre = request.getParameter("genre");
        // Vérification de l’âge
        int age = -1;
        try {
            age = Integer.parseInt(modele.age);
            modele.ageOk = age > 0;
        } catch (NumberFormatException ex) {
            modele.ageOk = false;
        }
        // Construction du message et des alertes.
        if (modele.ageOk) {
            modele.personne = new Personne(modele.genre.equals("Homme"), modele.nom, modele.prenom, age);
            modele.message = String.format("Bonjour %s %s %s, (%d an%s)",
                    (modele.personne.isMonsieur() ? "M." : "Mme"),
                    modele.personne.getPrenom(),
                    modele.personne.getNom(),
                    modele.personne.getAge(),
                    modele.personne.getAge()>1 ? "s" : "");
        } else {
            modele.setMessage("L'âge doit être un entier supérieur à 0 écrit en chiffres");
        }
        // Placement du modèle dans la requête.
        request.setAttribute("modele", modele);
    }
}
