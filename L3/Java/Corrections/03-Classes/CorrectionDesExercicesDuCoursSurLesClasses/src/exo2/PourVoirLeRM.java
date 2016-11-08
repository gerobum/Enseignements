/*
 Écrire une classe (PourVoirLeRM) capable de compter le nombre d’instances créées et dont
 chaque instance possède un attribut donnant son numéro d’ordre de création. Quand une instance
 de type PourVoirLeRM est détruite par le « ramasse-miettes », elle affiche dans la console un
 message indiquant son numéro d’ordre et le nombre d’instances de type PourVoirLeRM créées (voir
 la méthode finalize()). Dans le même temps, elle met fin à l’application autonome qui l’a lancée
 (Voir la classe System)
 Écrire une application autonome qui crée suffisamment d’instances de PourVoirLeRM non
 référencées pour que le ramasse-miette les détruise.

 */
package exo2;

/**
 *
 * @author yvan
 */
public class PourVoirLeRM {

    // Pour compter le nombre d'instances créées, un attribut de classe est nécessaire

    private static int nbInstances;
    // Pour identifier une instance, un attribut d'instance est nécessaire
    private final int numero;
    // Pour alourdir les objets, plus grand sera le tableau et plus précose sera la destruction
    private final double[] lest = new double[1000];

    public PourVoirLeRM() {
        // Incrémentation de nbInstances puis affectation de numero
        numero = ++nbInstances;
    }

    /*
     Cette méthode est appelée quand l'objet est détruit
     */
    @Override
    protected void finalize() throws Throwable {
        System.err.println("Destruction de l'instance numéro " + numero);
        // Pour fermer l'application à la première destruction
        System.exit(numero);
    }

}
