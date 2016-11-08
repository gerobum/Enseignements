#ifndef FRACTION_H
#define FRACTION_H

#include <iostream>

using namespace std;

class Fraction {
private:
    int d_n;
    int d_d;
public:

    Fraction(int n, int d) : d_n(n), d_d(d) {
    }
    Fraction& operator=(const Fraction&);
    Fraction& operator++(); // pré-incrémentation
    const Fraction operator++(int); // post
    operator double();
    int num() const;
    int den() const;
    
    friend ostream& operator<<(ostream& os, const Fraction& f);
};
Fraction operator*(const Fraction& a, const Fraction& b);



#endif /* FRACTION_H */

