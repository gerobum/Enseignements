package demos_du_cours.exemple1;

/**
 *
 * @author maillot
 */
public @interface PreambuleClasse {
    String auteur(); // Déclarée comme des fonctions,
    String creation();// mais ce sont bien des propriétés
    String revision();// qui auront une valeur.
    int version();
}
