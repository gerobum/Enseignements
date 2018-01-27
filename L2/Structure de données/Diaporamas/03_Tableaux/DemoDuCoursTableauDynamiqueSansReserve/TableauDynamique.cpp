#include "TableauDynamique.h"

TableauDynamique::TableauDynamique() : d_table{0}, d_n{0} {
}
int TableauDynamique::dimension() const {
    return d_n;
}
void TableauDynamique::ajouter(double v) {
    double* table = new double[d_n+1];
    for(int i = 0; i < d_n; ++i) {
        table[i] = d_table[i];
    }
    delete d_table;
    table[d_n++] = v;
    d_table = table;
}
double& TableauDynamique::operator [](int i) {
    return d_table[i];
}

TableauDynamique::~TableauDynamique() {
    delete d_table;
}





