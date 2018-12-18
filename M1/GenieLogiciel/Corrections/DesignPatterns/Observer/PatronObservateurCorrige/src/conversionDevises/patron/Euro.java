package conversionDevises.patron;

/**
 * L'Observé est un Euro. La valeur que l'on considère est une valeur "double"
 * que l'on affichera dans la devise choisie.
 * @author Yvan
 */
public class Euro extends Observé<Double> {
    private Double valeurEn€;

    public Euro(Double valeur) {
        this.valeurEn€ = valeur;
    }
    /**
     * Donne la valeur de l'observé (le sujet)
     * @return Une valeur en €
     */
    public Double getValeur() {
        return valeurEn€;
    }
    /**
     * Mettre à jour la valeur en €
     * @param valeurEn€ en €
     */
    public void setValeur(Double valeurEn€) {
        this.valeurEn€ = valeurEn€;
        notifier();
    } 



}
