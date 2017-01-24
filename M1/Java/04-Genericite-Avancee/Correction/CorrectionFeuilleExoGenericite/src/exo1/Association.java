package exo1;

/*
En s’inspirant de la classe Couple vue en cours, dans un paquetage exo1, écrire 
une classe Association qui associe deux éléments de types différents.
*/
public class Association<T,U> {
    public final T un;
    public final U deux;

    public Association(T un, U deux) {
        this.un = un;
        this.deux = deux;
    }   
}
