package exo2;

import exo1.*;

/*
En s’inspirant de la classe Triplet vue en cours, dans un paquetage exo2, 
écrire une classe Transition qui associe trois éléments de types différents.
*/
public class Transition<T, U, V> extends Association<T, U> {

    public final V trois;

    public Transition(T un, U deux, V trois) {
        super(un, deux);
        this.trois = trois;
    }
    
}
