/* 
 * File:   main.cpp
 * Author: yvan
 *
 * Created on 26 mars 2014, 11:05
 */


#include <iostream>
#include <list>
#include "suite.h"

using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    cout << "Test" << endl;
    suite s;
    suite::iterateur i = s.premier();
    i.inserer("A").inserer("B").inserer("C");

    cout << s << endl; // [A, B, C]
    i << "X" << "Y" << "Z";
    suite copy{s};
    cout << s << endl; // [A, B, C, X, Y, Z]
    ------i; // Décrémentation trois fois
    i << "D" << "E" << "F";
    cout << s << endl;// [A, B, C, D, E, F, X, Y, Z]
   ------i; // Décrémentation trois fois
    string d, e, f;
    i >> d >> e >> f; // "D" dans d, "E" dans e, "F" dans e
    cout << d << ", " << e <<", "<<f<<" ont été enlevés"<<endl;
    cout << s << endl; // [A, B, C, X, Y, Z]
    suite t(s); // Constructeur par recopie
    cout << t << endl; // [A, B, C, X, Y, Z]
    for(suite::iterateur i = s.premier(), fin = s.dernier(); i != fin; ++i)
    {
        (*i)[0] += 'a' - 'A'; // Première lettre en minuscule
    }
    cout << s << endl; // [a, b, c, x, y, z]
    cout << t << endl; // [A, B, C, X, Y, Z]
    i = t.dernier();
    //i >> d; // Rien n'est retiré
    cout << d << endl; // @
    cout << t << endl; // [A, B, C, X, Y, Z]
    ------i;
    while(i != t.dernier()) {
        i.retirer();
        cout << t << endl; // [A, B, C, Y, Z],[A, B, C, Z],[A, B, C]
    }
    i = t.premier();
    while(i != t.dernier()) {
        i.retirer();
    }

    cout << "-----------------" << endl;
    cout << "1. " << copy << endl;
    cout << "2. " << t << endl;
    cout << "-----------------" << endl;
    cout << "-----------------" << endl;
    cout << "1. " << copy << endl;
    t = copy;
    cout << "2. " << t << endl;
    cout << "-----------------" << endl;
    {
        list<int> l1;
        for (int i = 1; i <= 10; ++i) {
            l1.push_back(i);
        }

        for (list<int>::iterator i = l1.begin(); i != l1.end(); ++i) {
            cout << *i << " ";
        }
        cout << endl;
        l1.erase(l1.begin());
        cout << "--------FIN---------" << endl;
        for (list<int>::iterator i = l1.begin(); i != l1.end(); ++i) {
            cout << *i << " ";
        }
        cout << endl;
        l1.erase(l1.end());
        cout << "--------FIN---------" << endl;
        for (list<int>::iterator i = l1.begin(); i != l1.end(); ++i) {
            cout << *i << " ";
        }
        cout << endl;


        //l1.erase(il1);
        cout << "--------FIN---------" << endl;
    }
}

