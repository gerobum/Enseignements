package test;

import java.util.Scanner;
import suite.Syracuse;

public class Test {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        do {
            System.out.print("Un entier > ");
            n = in.nextInt();
            Syracuse s = new Syracuse(n);
            System.out.print(n);
            while (s.hasNext()) {
                System.out.print(", " + s.next());
            }
            System.out.println();
        } while (n > 0);
    }
}
