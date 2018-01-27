/* 
 * File:   TableauDynamique.h
 * Author: maillot
 */

#ifndef TABLEAU_DYNAMIQUE_H
#define	TABLEAU_DYNAMIQUE_H
// Une classe pour engendrer des tableaux dynamiques
class TableauDynamique { 
public : // Son interface
    int dimension() const; // Connaître sa dimension
    double& operator[](int i); // Lire et écrire les valeurs de ses éléments.
    void ajouter(double v); // Ajouter v à la fin du tableau.  
    // D'autres méthodes pourraient être ajoutées
    // comme supprimer une valeur, en insérer une ou plusieurs...
    TableauDynamique(); // Crée une table vide 
    // Pas de destructeur
private : // La structure de données :
    static const int MAX_ELTS{1000};
    double d_table[MAX_ELTS]; // un tableau dynamique
    int d_n; // et un entier pour la longueur
};

#endif	/* TABLEAU_DYNAMIQUE_H */

