package exemplepatronDecorateur;

public class Test {

    public static void main(String[] args) {
        Composant c1 = new ComposantConcret();
        System.out.println(c1);
        Composant c2 = new DecorateurConcretA(c1);
        System.out.println(c2);
        Composant c3 = new DecorateurConcretB(c2);
        System.out.println(c3);
        Composant c4 = new DecorateurConcretA(c3);
        System.out.println(c4);
    }

}
