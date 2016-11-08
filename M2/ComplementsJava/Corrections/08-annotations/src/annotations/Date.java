package annotations;

import types.Jour;
import types.Mois;

public @interface Date {
    Jour j();
    Mois m();
    int a();
}
