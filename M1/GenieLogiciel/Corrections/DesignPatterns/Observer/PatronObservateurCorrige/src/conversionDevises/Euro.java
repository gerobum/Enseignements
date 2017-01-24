package conversionDevises;

/**
 * L'Observé est un Euro. La valeur que l'on considère est une valeur "double"
 * que l'on affichera dans la devise choisie.
 * @author Yvan
 */
public class Euro<T> extends Observé<T> {
    private T valeur;

    public Euro(T valeur) {
        this.valeur = valeur;
    }
    /**
     * La méthode redéfinie transforme le type de retour que l'on sait
     * etre un Double. Elle est également rendue publique.
     */
    @Override
    public T getValeur() {
        return valeur;
    }
    /**
     * La méthode redéfinie reste protégée (protected) pour obliger la mise à
     * jour de valeur de type Double grâce à la méthode suivante. 
     */
    @Override
    public void setValeur(T valeur) {
        this.valeur = valeur;
        notifier();
    } 



}
