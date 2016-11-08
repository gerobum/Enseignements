package demos_du_cours.exemple3;

/**
 *
 * @author maillot
 */
public @interface PreambuleClasse {
    String auteur();  
    String creation(); 
    String[] revision(); 
    Version version();
}
