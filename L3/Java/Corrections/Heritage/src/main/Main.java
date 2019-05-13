/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package main;

import checker.Checker;
import vocabulaire.A;
import vocabulaire.B;
import vocabulaire.C;
import vocabulaire.D;

/**
 *
 * @author yvan
 */
public class Main {

    public static void main(String[] args) throws NoSuchMethodException {
        Checker chk = new Checker(A.class);
        System.out.println(chk);
        System.out.println("--------------------");
        chk = new Checker(B.class);
        System.out.println(chk);

        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();

        System.out.println(a.quiSuisEtdOuViensJe());
        System.out.println(b.quiSuisEtdOuViensJe());
        System.out.println(c.quiSuisEtdOuViensJe());
        System.out.println(d.quiSuisEtdOuViensJe());

        //Arrays.stream(D.class.getDeclaredMethods()).forEach(System.out::println);
        //Arrays.stream(D.class.getMethod()).forEach(System.out::println);
        System.out.println(D.class.getDeclaredMethod("quiSuisJe"));
        System.out.println(D.class.getMethod("quiSuisJe"));

        int[] t = new int[25];
        for (int i = 2; i < t.length; ++i) {
            t[i] = i;
        }
        for (int i = 0; i < t.length; ++i) {
            if ((t[i] == 2 || t[i] % 2 != 0) && 
                (t[i] == 3 || t[i] % 3 != 0) && 
                (t[i] == 5 || t[i] % 5 != 0)) {
                System.out.println(t[i]);
            }
        }
        
    }
}
