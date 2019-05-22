/* 
 * File:   map.cpp
 * Author: yvan
 * 
 * Created on 3 avril 2014, 18:41
 */

#include "map.h"
#include "suite_abonnements.h"

map::map() {}

map::map(const map& orig) : d_abonnes(orig.d_abonnes) {
}
int map::hachage(const string& num) const  {
  return (num[8] - '0')*10 + (num[9] - '0');
}
bool map::estDans(const string& num) const {
  // Calcul de l'indice avec la fonction de hachage
  int p = hachage(num);
  // si num est enregistré,
  // il est dans la suite d'indice p.
  for (suite_abonnements::iterateur_constant i = d_abonnes[p].premier(),
          fin = d_abonnes[p].dernier(); i != fin; ++i) {
    if ((*i).d_num == num) // si le numéro existe,
      return true; // vrai est retourné
  }// Arrivé ici, rien n'a été trouvé 
  return false;
}
bool map::estDans(const string& num, suite_abonnements::iterateur& i) {
  // Calcul de l'indice avec la fonction de hachage
  int p = hachage(num);
  // si num est enregistré, 
  // il est dans la suite d'indice p.
  suite_abonnements::iterateur fin = d_abonnes[p].dernier();
  for (i = d_abonnes[p].premier();
          i != fin; ++i) {
    if ((*i).d_num == num) // si le numéro existe,
      return true; // vrai est retourné
  } // L'itérateur i donne l'abonné associé à ce numéro
  // Arrivé ici, rien n'a été trouvé 
  return false; 
  // Remarque, l'itérateur i correspond à "personne".
}

 
  // Associer un numéro à une personne
personne map::mettre(const string& num, const personne& p) {
  personne x("", "");
  if (num.length() != 10) {
    return x;
  } else {
    int index = hachage(num);
    suite_abonnements::iterateur i = d_abonnes[0].premier();
    if (estDans(num, i)) {
      x = (*i).getPersonne();
      (*i) = abonnement(num, p);
      return x;
    } else {
      d_abonnes[index].ajouter_en_tete(abonnement(num, p));
      return x;
    }
  }
}

personne map::recuperer(const string& num) {
  suite_abonnements::iterateur i = d_abonnes[0].premier();
  estDans(num, i);
  return (*i).d_personne;
}

ostream& operator<<(ostream& os, const map& m) {
  for (int i = 0; i < 100; i++) {
    if (!m.d_abonnes[i].vide()) {
      os << m.d_abonnes[i] << endl;
    }
  }
  return os;
}

