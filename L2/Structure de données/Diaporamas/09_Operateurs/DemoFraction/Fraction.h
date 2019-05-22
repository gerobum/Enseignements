#ifndef FRACTION_H
#define FRACTION_H

#include <iostream>

using namespace std;

class fraction {
private:
    int d_n;
    int d_d;
public:

    fraction(int n, int d) : d_n(n), d_d(d) {
    }
    fraction& operator=(const fraction&);
    fraction& operator++(); // pré-incrémentation
    const fraction& operator++(int); // post
    // Depuis c++11, il est possible d'ajouter le mot clé explicit
    // qui oblige la conversion explicit
    explicit operator double(); // depuis c++11 
    int num() const;
    int den() const;
    
    friend ostream& operator<<(ostream& os, const fraction& f);
};
fraction operator*(const fraction& a, const fraction& b);



#endif /* FRACTION_H */

