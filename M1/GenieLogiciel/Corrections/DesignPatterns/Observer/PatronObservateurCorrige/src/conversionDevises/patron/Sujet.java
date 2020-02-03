

package conversionDevises.patron;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe abstraite Observé : (l'observé parfois appelé le sujet) est l'objet 
 * que l'on veut "afficher" selon différents aspects et/ou modifier selon 
 * différents moyens.
 * 
 * La classe est abstraite. Cela permet d'avoir différents "Observés" avec 
 * des comportements nuancés.
 * Un autre avantage est de créer une fois pour toute certains comportements
 * comme l'ajout ou le retrait d'observateur ainsi que la notification.

 * @author Yvan
 * @param <T> Le type de l'objet observé
 */
public abstract class Observé<T> {
    
    private final Set<Observateur> observateur = new HashSet<>();
    
    /**
     * Pour ajouter un observateur (Attach)
     * @param o l'observateur à ajouter.
     */
    public void ajoute(Observateur o) {
        observateur.add(o);
    }
    /**
     * Pour retirer un observateur (Detach)
     * @param o l'observateur à retirer.
     */
    public void retire(Observateur o) {
        observateur.remove(o);
    }
    
    /**
     * Pour alerter tous les observateurs d'un changement de l'observé.
     */
    public void notifier() {
        for(Observateur o : observateur) {
            o.update();
        }
    }  
}
