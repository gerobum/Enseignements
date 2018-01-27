#include "TableauDynamique.h"


TableauDynamique::TableauDynamique() : d_n{0} {

}

int TableauDynamique::dimension() const {
    return d_n;
}

void TableauDynamique::ajouter(double v) {
    d_table[d_n++] = v;
}

double& TableauDynamique::operator [](int i) {
    return d_table[i];
}
