package page_4;

public enum Jour {
// Appel du constructeur à un paramètre
// pour les jours travaillés.
    LUNDI("Lundi"), MARDI("Mardi"), MERCREDI("Mercredi"),
    JEUDI("Jeudi"), VENDREDI("Vendredi"),
    // Appel du constructeur par défaut pour le weekend.
    SAMEDI, DIMANCHE;
    public final String nom;

    Jour() {
        this.nom = name();
    }

    Jour(String nom) {
        this.nom = nom;
    }
}