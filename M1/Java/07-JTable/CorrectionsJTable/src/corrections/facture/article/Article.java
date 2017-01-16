/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package corrections.facture.article;

/**
 *
 * @author Yvan
 */
public class Article {
    private double prix;
    private String nom;
    
    public Article(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
        
    }
    public double getPrix() {
        return prix;
    }
    
    @Override
    public String toString() {
        return nom;
    }
}
