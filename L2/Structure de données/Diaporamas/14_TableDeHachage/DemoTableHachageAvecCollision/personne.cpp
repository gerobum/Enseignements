/* 
 * File:   personne.cpp
 * Author: yvan
 * 
 * Created on 3 avril 2014, 18:09
 */

#include "personne.h"

personne::personne(const string& nom, const string& prenom) : d_nom(nom), d_prenom(prenom) {
}

personne::personne(const personne& orig) : d_nom(orig.d_nom), d_prenom(orig.d_prenom) {
}

string personne::getNom() const {
  return d_nom;
}

string personne::getPrenom() const {
  return d_prenom;
}

personne& personne::operator=(const personne& a) {
  if (this != &a) {
    d_nom = a.d_nom;
    d_prenom = a.d_prenom;
  }
  return *this;
}
ostream& operator<<(ostream& os, const personne& c) {
  os << c.getPrenom() << ' ' << c.getPrenom();
  return os;
}

