package exo4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/*
1. Dans un paquetage exo4, créer une classe qui réalise une pile générique basée 
   sur une liste chaînée.
2. On doit trouver les méthodes empiler, dépiler, vide.
3. Ajouter une méthode qui crée un tableau à partir du contenu de la pile.
4. Ajouter un constructeur qui crée une pile à partir d’un tableau ou d’une 
   collection.
5. Redéfinir les méthodes de la classe Object.
6. Utiliser la pile pour analyser une expression arithmétique écrite en notation 
   polonaise
*/
public class Pile<T> implements Cloneable {

    private static class Chainon<T> {

        private final T info;
        private final Chainon<T> suivant;

        private Chainon(T info, Chainon<T> suivant) {
            this.info = info;
            this.suivant = suivant;
        }
    }

    private Chainon<T> sommet;

    public Pile() {
        sommet = null;
    }

    public Pile(T[] t) {
        for (T e : t) {
            sommet = new Chainon<>(e, sommet);
        }
    }

    public Pile(Collection<T> c) {
        for (T e : c) {
            sommet = new Chainon<>(e, sommet);
        }
    }

    public void empiler(T e) {
        sommet = new Chainon<>(e, sommet);
    }

    public T depiler() {
        T r = sommet.info;
        sommet = sommet.suivant;
        return r;
    }

    public boolean vide() {
        return sommet == null;
    }

    public T[] toArray() {
        if (sommet == null) {
            return (T[]) new Object[0];
        } else {
            int n = 0;
            Chainon<T> crt = sommet;
            while (crt != null) {
                ++n;
                crt = crt.suivant;
            }
            T[] to;
            to = (T[]) Array.newInstance(sommet.info.getClass(), n);

            crt = sommet;
            while (crt != null) {
                to[--n] = crt.info;
                crt = crt.suivant;
            }
            return to;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.sommet);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pile<?> other = (Pile<?>) obj;

        return Arrays.deepEquals(toArray(), other.toArray());
    }

    @Override
    public Pile<T> clone() {
        try {
            Pile<T> p = (Pile<T>) super.clone();
            Chainon<T> crt = sommet;
            while (crt != null) {
                p.empiler(crt.info);
                crt = crt.suivant;
            }
            return p;
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
}
