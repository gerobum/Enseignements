/*
Écrire une classe AutoCompteur dont l’instance affiche à sa construction le nombre d’instances
de AutoCompteur créées.
 */
package exo1;

/**
 *
 * @author yvan
 */
public class AutoCompteur {
    // Pour compter le nombre d'instances créées, un attribut de classe est nécessaire
    private static int nbInstances;

    public AutoCompteur() {
        System.out.println(++nbInstances);
    }

    // Un getter de classe pour accéder à un attribut de classe.
    public static int getNbInstances() {
        return nbInstances;
    }
}
