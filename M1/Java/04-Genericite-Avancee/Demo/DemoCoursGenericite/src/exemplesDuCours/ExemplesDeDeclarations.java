package exemplesDuCours;

import java.awt.Color;
import java.util.List;

public class ExemplesDeDeclarations {

    public static void main(String[] args) {
        List<String> ls; // Une liste de chaînes
        List<Color> lc; // de couleurs
// List<int> li; // Pas possible avec des types primitifs
        List<Integer> li;// Une liste d’entiers.
        List<Article> la; // d’un type défini par le programmeur
        List<List<Integer>> lli; // Et même pourquoi pas, une liste de listes d’entiers.
        List<String[]> lts; // Une liste de tableaux de String
        List<Integer[][]> lt2d;// Une liste de tableaux 2D
    }

    private static class Article {
        public Article() {
        }
    }
}
