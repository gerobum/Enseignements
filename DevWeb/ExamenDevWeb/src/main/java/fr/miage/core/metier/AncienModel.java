/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package fr.miage.core.metier;

import fr.miage.core.entity.Ancien;
import fr.miage.core.repository.AncienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author yvan
 */
@Component
public class AncienModel {


    private Long id;

    private String nom;

    private String prenom;

    private String promo;

    public AncienModel(Ancien ancien) {
        this.id = ancien.getId();
        this.nom = ancien.getNom();
        this.prenom = ancien.getPrenom();
        this.promo = ancien.getPromo() + "";
    }

    public AncienModel() {
        this(new Ancien());
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

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " (promo " + promo + ")";
    }
    
    public Ancien getAncien() {
        return new Ancien(nom, prenom, Integer.parseInt(promo));
    }
}
