package annotations;

/*
* 7 Exercices
* 7.1 Date
* Reprendre le type d’annotation PreambuleClasse de ce cours pour qu’une date ne 
* soit pas une chaîne de caractères mais un type d’annotation Date à créer.
*/
public @interface PreambuleClasse {

    String auteur();

    Date creation();

    Date[] revision();

    Version version(); // Un type d’annotation

}
