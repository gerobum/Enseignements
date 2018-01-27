/* 
 * File:   main.cpp
 * Author: yvan
 *
 * Created on 11 février 2014, 10:39
 */

#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    {
        int a = 10;
        int& c = a;
        double b{2.5};
        cout << c << endl; //10
        c = 2;
        cout << a << endl; //2
        int *pa = &a;
        double *p;
        p = new double{5};
        p = &b;
        // Hélas, cette zone ne pourra plus être libérée
        // delete p;
        // Trois fois hélas, ce "delete" provoque un plantage
        // car il ne pointe plus vers une zone libérable.
    }
    {
        int *a = new int{10};
        double *b = new double{2.5};
        double *c = new double;
        double *d;
        delete b;
        delete c;
        d = new double[3];
        d[0] = 1;
        d[1] = 2;
        d[2] = 3;
        delete a;
        // Augmentation dynamique de d
        // Déclaration d’un pointeur temporaire
        double *nd = new double[4];
        for (int i = 0; i < 3; i++)//recopie du tableau
            nd[i] = d[i];
        delete[] d;
        nd[3] = 4;
        d = nd;
        
        string sss{"salut"};
        cout << sss << endl;
        
        string *ttt;
        
        ttt = new string{"au revoir"};
        cout << ttt->size() << endl;
        cout << *ttt << endl;
    }
}

