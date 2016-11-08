package cartes;

import interfaces.Compartimentable;
import interfaces.Compartimenteur;
import java.util.Random;
import tri.TrieurParCompartiment;

/**
 *
 * @author yvan
 */
public class JeuDeBelote {

    private static final Random RANDOM = new Random();

    // Jeux de carte (noir) : ♥ ♠ ♦ ♣
    //(coeur pique carreau trêfle plein : 0x2665, 0x2660, 0x2666, 0x2663)
    //Jeux de carte (blanc) : ♡ ♤ ♢ ♧
    //(idem en contour : 0x2661, 0x2664, 0x2662, 0x2667) 
    private static final char[] COULEUR = {'\u2661', '\u2660', '\u2662', '\u2663'};
    private static final char[] FIGURE = {'7', '8', '9', 'X', 'V', 'D', 'R', 'A'};

    public final class Carte implements Compartimentable {

        public final int I_COULEUR;
        public final int I_FIGURE;

        private Carte(int iCouleur, int iFigure) {
            this.I_COULEUR = iCouleur;
            this.I_FIGURE = iFigure;
        }

        @Override
        public int getNumberOfCompartiments() {
            return 32;
        }

        @Override
        public int getCompartiment() {
            return I_COULEUR * 8 + I_FIGURE;
        }

        @Override
        public String toString() {
            return FIGURE[I_FIGURE] + "" + COULEUR[I_COULEUR];
        }
    }

    private final Carte[] JEU;

    {
        JEU = new Carte[32];
        int k = 0;
        for (int c = 0; c < COULEUR.length; c++) {
            for (int f = 0; f < FIGURE.length; f++) {
                JEU[k++] = new Carte(c, f);
            }
        }
    }

    private void echanger(int i) {
        int p = RANDOM.nextInt(i + 1);
        Carte tmp = JEU[p];
        JEU[p] = JEU[i];
        JEU[i] = tmp;
    }

    public void melanger() {
        for (int i = JEU.length - 1; i > 0; --i) {
            echanger(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < JEU.length; ++i) {
            sb.append(JEU[i]).append(" ");
        }
        return sb.toString();
    }
    
    public void trier(Compartimenteur compartimenteur) {
        TrieurParCompartiment tpc = new TrieurParCompartiment(JEU, compartimenteur);
        tpc.trier();
    }
    
    public void trier() {
        trier(null);
    }
}
