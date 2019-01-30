package exos3_2;

import java.util.Scanner;

/**
 *
 * @author yvan
 */
public class ManipulationChainetique {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String mot;
        do {
            System.out.print("Entrez une chaîne de caractères : ");
            mot = in.next();

            System.out.println(mot.toUpperCase());
            if (estUnPalindrome(mot)) {
                System.out.println(mot + " est un palindrome");
            } else {
                System.out.println(mot + " n'est pas un palindrome");
            }
            
            System.out.print("Entrez un caractère : ");
            char c = in.next().charAt(0);
            
            if (mot.contains(c+"")) {
                System.out.println(c + " est dans " + mot);
            } else {
                System.out.println(c + " n'est pas dans " + mot);
            }
            
            int nb = 0;
            int p = mot.indexOf(c);
            
            while(p >= 0) {
                ++nb;
                p = mot.indexOf(c+"", p+1);
            }
            System.out.println(c + " apparait " + nb + " fois dans " + mot);
        } while (!"fin".equals(mot));
    }

    static boolean estUnPalindrome(String mot) {
        int i = 0, j = mot.length() - 1;

        while (i < j && mot.charAt(i) == mot.charAt(j)) {
            ++i;
            --j;
        }
        return i >= j;
    }
}
