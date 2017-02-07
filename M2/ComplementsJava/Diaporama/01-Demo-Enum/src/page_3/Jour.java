package page_3;

public enum Jour {
    LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE;

    @Override
    /* Redéfinition de toString() pour qu’elle retourne le nom du jour avec la première
lettre en majuscule et les autres en minuscule. */
    public String toString() {
        return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
    }

    public int numéro() {
        /* Ajout d’une méthode qui retourne 1 pour lundi, 2 pour mardi... */
        return ordinal() + 1;
    }
}
