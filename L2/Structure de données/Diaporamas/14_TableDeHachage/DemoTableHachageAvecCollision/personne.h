/* 
 * File:   personne.h
 * Author: yvan
 *
 * Created on 3 avril 2014, 18:09
 */

#ifndef PERSONNE_H
#define	PERSONNE_H

#include<string>
#include<ostream>

using namespace std;

class personne {
private: // Une personne porte un prénom et un nom
  string d_nom, d_prenom; 
public:
  personne(const string& prenom, const string& nom); // son constructeur naturel
  personne(const personne& orig); // Le constructeur par recopie
  string getNom() const; 
  string getPrenom() const;
  personne& operator=(const personne& a); // L'affectation est nécessaire.
}; // Redirection du flux
ostream& operator<<(ostream& os, const personne& c);

#endif	/* PERSONNE_H */

