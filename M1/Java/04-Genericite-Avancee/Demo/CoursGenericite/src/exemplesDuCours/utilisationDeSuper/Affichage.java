package exemplesDuCours.utilisationDeSuper;

public class Affichage<T> implements Traitement<T> {
    @Override
    public void traiter(T t) {
        System.out.println(t);
    }
}
