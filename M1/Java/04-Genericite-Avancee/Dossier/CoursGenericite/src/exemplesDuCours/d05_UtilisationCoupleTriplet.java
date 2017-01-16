package exemplesDuCours;

public class d05_UtilisationCoupleTriplet {

      public static void main(String[] args) {
           d03_Couple<Integer> c = new d03_Couple<>(1, -1);
           d04_Triplet<String> t = new d04_Triplet<>("1", "un", "1.0");
           System.out.println(c.un + ", " + c.deux);
           System.out.println(t.un+", "+t.deux+", "+ t.trois);
      }
}
