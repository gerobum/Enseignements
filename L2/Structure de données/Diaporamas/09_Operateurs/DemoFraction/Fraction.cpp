#include "Fraction.h"

// Avec l'amitié
ostream& operator<<(ostream& os, const Fraction& f) {
    // L’accès aux membres privés est permis
    // car operator<< est ami de Fraction
    os << f.d_n << '/' << f.d_d;
    return os;
}
// Sans l'amitié
/*ostream& operator<<(ostream& os, const Fraction& f) {
    // L’accès aux membres privés est permis
    // car operator<< est ami de Fraction
    os << f.d_n << '/' << f.d_d;
    return os;
}*/

Fraction operator*(const Fraction& a, const Fraction& b) {
    Fraction r(a.num() * b.num(), a.den() * b.den());
    return r;
}

Fraction& Fraction::operator++() {
    d_n += d_d;
    return *this;
}

const Fraction Fraction::operator++(int) {
    const Fraction r = *this;
    operator++();
    return r;
}

Fraction& Fraction::operator=(const Fraction& b) {
    if (this != &b) {
        d_n = b.d_n;
        d_d = b.d_d;
    }
    return *this;
}

Fraction::operator double() {
    return static_cast<double> (d_n) / d_d;
}

int Fraction::num() const {
    return d_n;
}

int Fraction::den() const {
    return d_d;
}
