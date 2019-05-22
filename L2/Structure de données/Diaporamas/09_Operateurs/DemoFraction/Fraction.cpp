#include "Fraction.h"

// Avec l'amitié
ostream& operator<<(ostream& os, const fraction& f) {
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

fraction operator*(const fraction& a, const fraction& b) {
    fraction r(a.num() * b.num(), a.den() * b.den());
    return r;
}

fraction& fraction::operator++() {
    d_n += d_d;
    return *this;
}

const fraction& fraction::operator++(int) {
    const fraction& r(*this);
    operator++();
    return r;
}

fraction& fraction::operator=(const fraction& b) {
    if (this != &b) {
        d_n = b.d_n;
        d_d = b.d_d;
    }
    return *this;
}

fraction::operator double() {
    return static_cast<double> (d_n) / d_d;
}

int fraction::num() const {
    return d_n;
}

int fraction::den() const {
    return d_d;
}
