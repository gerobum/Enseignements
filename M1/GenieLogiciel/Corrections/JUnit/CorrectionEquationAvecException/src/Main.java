
import equation.Equation;
import exceptions.NulCoefException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author yvan
 */
public class Main {

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
                        
            System.out.println("Entrez a b et c");
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            Equation equation = new Equation(a, b, c);
            if (equation.getRootsCount()  == 0) {
                System.out.println("Pas de solution");
            } else if (equation.getRootsCount()  == 1) {
                System.out.println("Une seule racine : " + equation.getX1());
            } else {
                System.out.println("Deux racines : " + equation.getX1() + " et "
                        + equation.getX2());
            }
        } catch (NulCoefException ex) {
            System.out.println("Le coef a ne peut pas être nul.");
        }

    }

}
