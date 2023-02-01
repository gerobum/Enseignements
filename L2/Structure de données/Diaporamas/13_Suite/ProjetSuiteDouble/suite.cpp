
#include <iostream>
#include "suite.h"

suite::suite() : d_ancre{"@"} {
    d_ancre.d_suivant = &d_ancre;
    d_ancre.d_precedent = &d_ancre;
}

suite::~suite() {
    element* crt = d_ancre.d_suivant;
    while(crt != &d_ancre) {
        element* as = crt;
        crt = crt->d_suivant;
        delete as;
    }
}

suite::suite(const suite& orig) : d_ancre{"@"} {
    d_ancre.d_suivant = &d_ancre;
    d_ancre.d_precedent = &d_ancre;
    suite::iterateur i = premier();
    for (suite::iterateur_constant j = orig.premier(), fin = orig.dernier(); j != fin; ++j)
        i.inserer(*j);
}


suite& suite::operator=(const suite& src) {
    if (this != &src) {
        iterateur pdest = premier();
        iterateur_constant porig = src.premier();
        iterateur ddest = dernier();
        iterateur_constant dorig = src.dernier();
        // Pour éviter des allocations inutiles
        while (pdest != ddest && porig != dorig) {
            *pdest = *porig;
            ++pdest;
            ++porig;
        }
        // Vidage de la destination si l'origine est vide.
        while (pdest != ddest)
            pdest.retirer();
        // Insertion à la fin des éléments restants.
        while (porig != dorig) {
            pdest.inserer(*porig);
            ++porig;
        }
    }
    return *this;
}

ostream& operator<<(ostream& os, const suite& s) {
    os << "[";
    suite::iterateur_constant i = s.premier();
    suite::iterateur_constant fin = s.dernier();
    if (i != fin) {
        os << *i;
        ++i;
        while (i != fin) {
            os << ", " << *i;
            ++i;
        }
    }
    os << "]";
    return os;
}

void suite::ajouter_en_tete(string s) {
    element *n = new element{s};
    n->d_suivant = d_ancre.d_suivant;
    n->d_precedent = &d_ancre;
    n->d_suivant->d_precedent = n;
    n->d_precedent->d_suivant = n;
}

void suite::ajouter_en_queue(string s) {
  element *n = new element{s};
  n->d_suivant = &d_ancre;
  n->d_precedent = d_ancre.d_precedent;
  n->d_suivant->d_precedent = n;
  n->d_precedent->d_suivant = n;
}

void suite::retirer_en_tete() {
    element *as = d_ancre.d_suivant;
    d_ancre.d_suivant = as->d_suivant;
    d_ancre.d_suivant->d_precedent = &d_ancre;
    delete as;
}

string& suite::iterateur::operator *() {
    return d_crt->d_info;
}
string suite::iterateur::operator *() const {
    return d_crt->d_info;
}
bool suite::iterateur::operator==(const suite::iterateur& i) const {
    return i.d_crt == d_crt;
}
bool suite::iterateur::operator!=(const suite::iterateur& i) const {
    return i.d_crt != d_crt;
}
suite::iterateur& suite::iterateur::operator++() {
    d_crt = d_crt->d_suivant; return *this;
}
suite::iterateur& suite::iterateur::operator--() {
    d_crt = d_crt->d_precedent; return *this;
}
string suite::iterateur_constant::operator *() const {
    return d_crt->d_info;
}
bool suite::iterateur_constant::operator==(const suite::iterateur_constant& i) const {
    return i.d_crt == d_crt;
}
bool suite::iterateur_constant::operator!=(const suite::iterateur_constant& i) const {
    return i.d_crt != d_crt;
}
suite::iterateur_constant& suite::iterateur_constant::operator++() {
    d_crt = d_crt->d_suivant; return *this;
}
suite::iterateur_constant& suite::iterateur_constant::operator--() {
    d_crt = d_crt->d_precedent; return *this;
}
suite::iterateur suite::premier() {
    return suite::iterateur{&d_ancre, d_ancre.d_suivant};
}
// Le dernier est l’ancre.
suite::iterateur suite::dernier() {
    return suite::iterateur{&d_ancre, &d_ancre};
}
// C’est la même chose pour les itérateurs constants
suite::iterateur_constant suite::premier() const {
    return suite::iterateur_constant{&d_ancre, d_ancre.d_suivant};
}
suite::iterateur_constant suite::dernier() const {
    return suite::iterateur_constant{&d_ancre, &d_ancre};
}

suite::iterateur& suite::iterateur::inserer(const string& s) {
    element* n = new element{s};
    n->d_suivant = d_crt;
    n->d_precedent = d_crt->d_precedent;
    n->d_precedent->d_suivant = n;
    n->d_suivant->d_precedent = n;
    return *this;
}

suite::iterateur& suite::iterateur::operator<<(const string &s) {
    return inserer(s);
}

suite::iterateur& suite::iterateur::retirer() {
    //if (d_crt != d_ancre) {
        element* as = d_crt;
        d_crt->d_suivant->d_precedent = d_crt->d_precedent;
        d_crt->d_precedent->d_suivant = d_crt->d_suivant;
        d_crt = d_crt->d_suivant;
        delete as;
    //}
    return *this;
}
suite::iterateur& suite::iterateur::operator>>(string& s) {
  s = d_crt->d_info;
  return retirer();
}