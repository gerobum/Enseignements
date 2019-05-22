/* 
 * File:   abonnement.cpp
 * Author: yvan
 * 
 * Created on 3 avril 2014, 18:20
 */

#include "abonnement.h"


  abonnement::abonnement(const string& num, const personne& p) : d_num(num), d_personne(p) {
  }
  abonnement::abonnement(const abonnement &a)  : d_num(a.d_num), d_personne(a.d_personne) {
  }
  string abonnement::getNum() const {
    return d_num;
  }
  personne abonnement::getPersonne() const {
    return d_personne;
  }
  
  abonnement& abonnement::operator=(const abonnement& a) {
    if (this != &a) {
      d_num = a.d_num;
      d_personne = a.d_personne;
    }
    return *this;
  }

ostream& operator<<(ostream& os, const abonnement& a) {
  os << a.getNum() << " => " << a.getPersonne();
  return os;
}