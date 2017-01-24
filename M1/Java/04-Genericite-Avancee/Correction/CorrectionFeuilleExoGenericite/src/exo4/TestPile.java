
package exo4;


import java.util.Scanner;

public class TestPile {

    public static void main(String[] args) throws Exception {

        Scanner clavier = new Scanner(System.in);
        Pile<Double> p = new Pile<>();

        System.out.print("> ");
        while (clavier.hasNext()) {

            String o = clavier.next();
            try {
                p.empiler(Double.parseDouble(o));
            } catch (NumberFormatException n) {
                switch (o) {
                    case "+":
                        p.empiler(p.depiler() + p.depiler());
                        break;
                    case "-":
                        p.empiler(-p.depiler() + p.depiler());
                        break;
                    case "*":
                        p.empiler(p.depiler() * p.depiler());
                        break;
                    case ":":
                        p.empiler(1 / p.depiler() * p.depiler());
                        break;
                    case "=":
                        System.out.println(p.depiler());
                        System.out.print("> ");
                        break;
                    default:
                        throw new Exception("Opérateur inconnu");
                }
            }
        }


    }
}
