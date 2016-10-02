package sudoku;

import java.util.ArrayList;
import java.util.List;

public class Grille {

    private final List<Zone9> ligne;
    private final List<Zone9> colonne;
    private final List<Zone9> carr�;

    {
        ligne = new ArrayList<>();
        colonne = new ArrayList<>();
        carr� = new ArrayList<>();
    }

    public Grille() {
        for (int i = 0; i < 9; ++i) {
            Zone9 z = new Zone9();

            ligne.add(new Zone9());
        }
    }

    public Zone9 getLigne(int i) {
        return ligne.get(i);
    }

    public Zone9 getColonne(int i) {
        return colonne.get(i);
    }

    public Zone9 getCarr�(int l, int c) {
        return carr�.get(l * c + c);
    }

}
