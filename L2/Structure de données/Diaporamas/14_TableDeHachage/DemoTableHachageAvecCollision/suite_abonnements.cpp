/* 
 * File:   suite_abonnements.cpp
 * Author: yvan
 * 
 * Created on 3 avril 2014, 18:30
 */

#include "suite_abonnements.h"

suite_abonnements::element::element()
// L'information par défaut est un abonnement
// dont le numéro est vide et dont la personne
// n'a ni nom ni prénom.
: d_info("", personne("", "")),
d_suivant(0), d_precedent(0) {
}

suite_abonnements::element::element(const abonnement& info) :
d_info(info),
d_suivant(0),
d_precedent(0) {
}

suite_abonnements::element::element(const element& e) :
d_info(e.d_info),
d_suivant(0),
d_precedent(0) {
}


// Le premier élément est celui après l'ancre

suite_abonnements::iterateur suite_abonnements::premier() {
  return suite_abonnements::iterateur(&d_ancre, d_ancre.d_suivant);
}
// Le dernier est l'ancre.

suite_abonnements::iterateur suite_abonnements::dernier() {
  return suite_abonnements::iterateur(&d_ancre, &d_ancre);
}
// C'est la même chose pour les itérateurs constants

suite_abonnements::iterateur_constant suite_abonnements::premier() const {
  return suite_abonnements::iterateur_constant(&d_ancre, d_ancre.d_suivant);
}

suite_abonnements::iterateur_constant suite_abonnements::dernier() const {
  return suite_abonnements::iterateur_constant(&d_ancre, &d_ancre);
}

abonnement& suite_abonnements::iterateur::operator *() {
  return d_crt->d_info;
}

abonnement suite_abonnements::iterateur::operator *() const {
  return d_crt->d_info;
}

bool suite_abonnements::iterateur::operator==(const suite_abonnements::iterateur& i) const {
  return i.d_crt == d_crt;
}

bool suite_abonnements::iterateur::operator!=(const suite_abonnements::iterateur& i) const {
  return i.d_crt != d_crt;
}

suite_abonnements::iterateur& suite_abonnements::iterateur::operator++() {
  d_crt = d_crt->d_suivant;
  return *this;
}

suite_abonnements::iterateur& suite_abonnements::iterateur::operator--() {
  d_crt = d_crt->d_precedent;
  return *this;
}

abonnement suite_abonnements::iterateur_constant::operator *() const {
  return d_crt->d_info;
}

bool suite_abonnements::iterateur_constant::operator==(const suite_abonnements::iterateur_constant& i) const {
  return i.d_crt == d_crt;
}

bool suite_abonnements::iterateur_constant::operator!=(const suite_abonnements::iterateur_constant& i) const {
  return i.d_crt != d_crt;
}

suite_abonnements::suite_abonnements() : d_ancre() {
  d_ancre.d_suivant = &d_ancre;
  d_ancre.d_precedent = &d_ancre;
}

suite_abonnements::iterateur_constant& suite_abonnements::iterateur_constant::operator++() {
  d_crt = d_crt->d_suivant;
  return *this;
}

suite_abonnements::iterateur_constant& suite_abonnements::iterateur_constant::operator--() {
  d_crt = d_crt->d_precedent;
  return *this;
}

void suite_abonnements::ajouter_en_tete(const abonnement& s) {
  element *n = new element(s);
  n->d_suivant = d_ancre.d_suivant;
  n->d_precedent = &d_ancre;
  n->d_suivant->d_precedent = n;
  n->d_precedent->d_suivant = n;
}


ostream& operator<<(ostream& os, const suite_abonnements& s) {

  suite_abonnements::iterateur_constant i = s.premier();
  suite_abonnements::iterateur_constant fin = s.dernier();
  if (i != fin) {
    os << *i;
    ++i;
    while (i != fin) {
      os << ", " << *i;
      ++i;
    }
  }

  return os;
}