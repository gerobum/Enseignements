#include "TableauDynamique.h"

TableauDynamique::TableauDynamique() : d_capacite{CI}, d_table{new double[CI]}, d_n{0} {}
int TableauDynamique::dimension() const {
    return d_n;
}
double& TableauDynamique::operator [](int i) {
    return d_table[i];
}
void TableauDynamique::ajouter(double v) {
    if (d_n == d_capacite) { // La capacité est atteinte
        d_capacite *= CM; // elle est augmentée
        double* table = new double[d_capacite];
        for(int i = 0; i < d_n; i++) {
            table[i] = d_table[i];
        }
        delete d_table;
        d_table = table;        
    }
    d_table[d_n++] = v;
}
TableauDynamique::~TableauDynamique() {
    delete d_table;
}


