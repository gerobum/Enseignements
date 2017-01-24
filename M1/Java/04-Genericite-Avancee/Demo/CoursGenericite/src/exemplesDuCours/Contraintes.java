package exemplesDuCours;

public class Contraintes<T> {

    public <U> void m(U u, T t) {
    }

    public static void main(String[] args) {
        Contraintes<Object> c = new Contraintes<>();

        c.m(java.awt.Color.BLACK, "1.0");
    }
}
