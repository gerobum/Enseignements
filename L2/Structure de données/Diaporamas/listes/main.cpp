/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: yvan
 *
 * Created on 28 janvier 2019, 20:39
 */

#include <cstdlib>
#include <iostream>

using namespace std;
struct Chainon {
        double info;
        Chainon* next;
    };
    typedef Chainon* Chaine;
/*
 * 
 */
int main(int argc, char** argv) {
    Chaine s;
    
    s = nullptr;
    
    Chaine tete = new Chainon;
    tete->info = 2;
    tete->next = nullptr;
    
    Chainon* crt = tete;
    while(crt != nullptr) {
        cout << crt->info;
        crt = crt->next;
    }

    return 0;
}

