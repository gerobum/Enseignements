#include "Fraction.h"

main() {
    Fraction a(1, 1), b(2, 3), c(1, 3);
    cout << static_cast<double> (c) << endl;
    // Conversion de type (affiche 0.3333)
    c = a*b;
    cout << c << endl; // 2/3
    // Depuis c++11, si la surcharge de conversion est déclarée explicit
    // alors il faut fait une conversion explicite comme ci-dessous
    double d = static_cast<double>(c);
    // en l'absence du mot clé explicit
    // double d = c; // Ceci suffit.
    cout << ++c << endl; // 5/3
    cout << d << endl; // 2/3
    cout << c++ << endl; // 5/3
    cout << ++++c << endl; // 14/3
    // cout << c++++ << endl; // impossible à compiler
}



