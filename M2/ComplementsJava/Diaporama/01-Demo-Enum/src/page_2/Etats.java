package page_2;

enum Etats {
// déclaration des constantes énumérées
    LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE;
// définition d’une constante
    private static final int NB_JOURS = 7;
// définition d’une méthode statique

    public static int getNbJours() {
        return NB_JOURS;
    }
}
