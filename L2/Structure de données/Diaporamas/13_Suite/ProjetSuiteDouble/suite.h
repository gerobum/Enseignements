/* 
 * File:   suite.h
 * Author: yvan
 *
 * Created on 26 mars 2014, 11:06
 */

#ifndef SUITE_H
#define	SUITE_H

#include<string>
#include<ostream>

using namespace std;

class suite {
public:
   suite();
   suite(const suite&);
   ~suite();

private:
    class element {
    public:
        string d_info;
        element *d_suivant;
        element *d_precedent;

        element(string info) :
                d_info{info},
                d_suivant{nullptr},
                d_precedent{nullptr} {}
    };

    element d_ancre;
public:
    suite& operator=(const suite& src);

    void ajouter_en_tete(string s);

    void ajouter_en_queue(string s);

    void retirer_en_tete();

    class iterateur {
    public:
        string operator *() const;
        string& operator *();
        iterateur& operator++();
        iterateur& operator--();
        bool operator==(const iterateur& i) const;
        bool operator!=(const iterateur& i) const;
        iterateur& inserer(const string& s);
        iterateur& retirer();
        iterateur& operator<<(const string& s);
        iterateur& operator>>(string& s);
        friend class suite;
    private:
        const element* d_ancre;
        element* d_crt;
        iterateur(const element* ancre, element* crt)
                : d_ancre{ancre}, d_crt{crt} { }
    };
    iterateur premier();
    iterateur dernier();

    class iterateur_constant {
    public:
        string operator *() const;
        iterateur_constant& operator++();
        iterateur_constant& operator--();
        bool operator==(const iterateur_constant& i) const;
        bool operator!=(const iterateur_constant& i) const;
        friend class suite;
    private:
        const element* d_ancre;
        const element* d_crt;
        iterateur_constant(const element* ancre, const element* crt) : d_ancre{ancre}, d_crt{crt} { }
    };
    iterateur_constant premier() const;
    iterateur_constant dernier() const;
};
ostream& operator<<(ostream& os, const suite& s);
#endif	/* SUITE_H */

