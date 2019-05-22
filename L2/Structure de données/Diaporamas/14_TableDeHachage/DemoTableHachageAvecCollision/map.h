/* 
 * File:   map.h
 * Author: yvan
 *
 * Created on 3 avril 2014, 18:41
 */

#ifndef MAP_H
#define	MAP_H

#include<string>
#include<ostream>
#include"suite_abonnements.h"

using namespace std;

class map {
public:
  map();  map(const map& orig);
private:
  // On prévoit un tableau de 100 suites d'abonnés
  suite_abonnements d_abonnes[100]; 
  int hachage(const string& num) const;// Fonction de hachage
  // Retourne vrai si le numéro num est enregistré.
  bool estDans(const string& num) const;
  // Idem et place l'itérateur i sur le bon abonné
 bool estDans(const string& num,suite_abonnements::iterateur& i);
public:
  // Associer un numéro à une personne
  personne mettre(const string& num, const personne& p);
  // Récupérer la personne dont le numéro est en paramètre
  personne recuperer(const string& num);
  friend ostream& operator<<(ostream& os, const map& m);
};
ostream& operator<<(ostream& os, const map& m);

#endif	/* MAP_H */

