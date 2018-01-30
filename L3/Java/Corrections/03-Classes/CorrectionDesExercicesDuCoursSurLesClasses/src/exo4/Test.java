package exo4;

/**
 *
 * @author yvan
 */
public class Test {
    public static void main(String[] args) {
        int[] njours = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//        String[] mois = {"janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"};                
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                Afficheur.printfSimplifie("% x % = %", i, j, i*j);
                System.out.println();
            }
        }
        
        for(int i = 0; i < njours.length; ++i) {
            Afficheur.printfSimplifie("Le mois % compte % jours\n", i+1, njours[i]);
        }
    }
}
