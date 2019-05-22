/* 
 * File:   suite_abonnements.h
 * Author: yvan
 *
 * Created on 3 avril 2014, 18:30
 */

#ifndef SUITE_ABONNEMENTS_H
#define	SUITE_ABONNEMENTS_H


#include<string>
#include<ostream>
#include"abonnement.h"

using namespace std;

class suite_abonnements {
private:

  class element { // Une structure récursive classique
  public:// L'information d'un chaînon est un abonnement.
    abonnement d_info;
    element * d_suivant;
    element * d_precedent;
    element();
    element(const abonnement& info);
    element(const element& e);
  };
  element d_ancre;
public:
  // Le reste est similaire à la suite de chaînes de caractères. 

  class iterateur {
  public:
    abonnement operator *() const;
    abonnement& operator *();
    iterateur& operator++();
    iterateur& operator--();
    bool operator==(const iterateur& i) const;
    bool operator!=(const iterateur& i) const;
    iterateur& inserer(string s);
    iterateur& retirer();
    iterateur& operator<<(const string& s);
    iterateur& operator>>(string& s);
    friend class suite_abonnements;
  private:
    const element* d_ancre;
    element* d_crt;

    iterateur(const element* ancre, element* crt) : d_ancre(ancre), d_crt(crt) {
    }
  };

  iterateur premier();
  iterateur dernier();

  class iterateur_constant {
  public:
    abonnement operator *() const;
    iterateur_constant& operator++();
    iterateur_constant& operator--();
    bool operator==(const iterateur_constant& i) const;
    bool operator!=(const iterateur_constant& i) const;
    friend class suite_abonnements;
  private:
    const element* d_ancre;
    const element* d_crt;

    iterateur_constant(const element* ancre, const element* crt) : d_ancre(ancre), d_crt(crt) {
    }
  };
  iterateur_constant premier() const;
  iterateur_constant dernier() const;

  suite_abonnements();
  void ajouter_en_tete(const abonnement& s);
  bool vide() const {return d_ancre.d_suivant == &d_ancre;}
};
ostream& operator<<(ostream& os, const suite_abonnements& s);
#endif	/* SUITE_ABONNEMENTS_H */

