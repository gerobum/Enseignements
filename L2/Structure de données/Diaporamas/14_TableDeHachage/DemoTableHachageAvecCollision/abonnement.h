/* 
 * File:   abonnement.h
 * Author: yvan
 *
 * Created on 3 avril 2014, 18:20
 */

#ifndef ABONNEMENT_H
#define	ABONNEMENT_H

#include<string>
#include<ostream>
#include"personne.h"

using namespace std;

class abonnement {
private:
  string d_num; // un numéro
  personne d_personne; // une personne
  abonnement(const string& num, const personne& p);
  abonnement(const abonnement &a);
  friend class map;
  friend class suite_abonnements; 
public:
  string getNum() const;
  personne getPersonne() const;
  abonnement& operator=(const abonnement& a);
};
ostream& operator<<(ostream& os, const abonnement& a);
#endif	/* ABONNEMENT_H */

