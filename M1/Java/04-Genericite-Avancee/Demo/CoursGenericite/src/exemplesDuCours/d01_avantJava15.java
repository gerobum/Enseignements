package exemplesDuCours;

import java.util.ArrayList;
import java.util.List;

public class d01_avantJava15 {

  public static void main(String[] args) {
    // Façon de faire avant Java 1.5
    List chaîne = new ArrayList();
    chaîne.add("un");
    chaîne.add("deux");
    List entiers = new ArrayList();
    entiers.add(new Integer(1));
    entiers.add(new Integer(2));
    int somme = 0;
    for (Object i : entiers)
      somme += (Integer) i;
    System.out.println(somme);
    // Le compilateur n'empêche pas cette grossière erreur.
    entiers.add("trois");
    somme = 0;
    for (Object i : entiers) 
      somme += ((Integer )i).intValue();   // Erreur une erreur à l'exécution
  }
}
