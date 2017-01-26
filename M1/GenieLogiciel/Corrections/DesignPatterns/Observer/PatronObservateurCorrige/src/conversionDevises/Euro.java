package conversionDevises;

/**
 * L'Observé est un Euro. La valeur que l'on considère est une valeur "double"
 * que l'on affichera dans la devise choisie.
 * @author Yvan
 */
public class Euro extends Observé<Double> {
    private Double valeur;

    public Euro(Double valeur) {
        this.valeur = valeur;
    }
    /**
     * Donne la valeur de l'observé (le sujet)
     * @return Une valeur en €
     */
    @Override
    public Double getValeur() {
        return valeur;
    }
    /**
     * Mettre à jour la valeur en €
     * @param valeur en €
     */
    @Override
    public void setValeur(Double valeur) {
        this.valeur = valeur;
        notifier();
    } 



}
