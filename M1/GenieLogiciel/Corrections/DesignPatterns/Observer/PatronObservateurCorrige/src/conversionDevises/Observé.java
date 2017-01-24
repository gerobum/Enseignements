

package conversionDevises;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe abstraite Observé : (l'observé parfois appelé le sujet) est l'objet 
 * que l'on veut "afficher" selon
 * différents aspects et/ou modifier selon différents moyens.
 * 
 * La classe est abstraite. Cela permet d'avoir différents "Observés" avec 
 * des comportements nuancés.
 * Un autre avantage est de créer une fois pour toute certains comportements
 * comme l'ajout ou le retrait d'obersvateur ainsi que la notification.
 * 
 * Même si, dans notre présente application, un seul type d'observé est suffisant.
 * @author Yvan
 * @param <T> Le type de l'objet observé
 */
public abstract class Observé<T> {
    
    private final Set<Observateur> observateur = new HashSet<>();
    
    public void ajoute(Observateur o) {
        observateur.add(o);
    }
    public void retire(Observateur o) {
        observateur.remove(o);
    }
    public void notifier() {
        for(Observateur o : observateur) {
            o.update();
        }
    }  
   
    protected abstract Object getValeur();
    protected abstract void setValeur(T o);
}
