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
package edu.uha.miage.web.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
public class Enseignant implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull@Pattern(regexp = "[A-Z]+", message = "Le nom doit être en majuscule")
    private String nom;
    @NotNull@Pattern(regexp = "[A-Z][a-z]*", message = "Le prénom doit commencer par une majuscule")
    private String prenom;
    private boolean genre;
    
    @OneToMany(mappedBy = "responsable")
    private List<Cours> responsabilite;
    
    @ManyToMany
    private List<Cours> enseigne;

    public Enseignant() {
    }

    public Enseignant(String prenom, String nom, boolean genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Cours> getResponsabilite() {
        return responsabilite;
    }

    public void setResponsabilite(List<Cours> responsabilite) {
        this.responsabilite = responsabilite;
    }

    public List<Cours> getEnseigne() {
        return enseigne;
    }

    public void setEnseigne(List<Cours> enseigne) {
        this.enseigne = enseigne;
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

    public boolean isGenre() {
        return genre;
    }

    public void setGenre(boolean genre) {
        this.genre = genre;
    }
    
    @Override
    public String toString() {
        return (genre?"Mme ":"M. ") + prenom + " " + nom;
    }
}
