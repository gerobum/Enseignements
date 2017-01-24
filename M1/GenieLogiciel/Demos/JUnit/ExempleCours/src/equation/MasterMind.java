package equation;


public class MasterMind {
  public MasterMind() {
  }
  
  /**
   * Retourne le nombre de valeurs égales et en m?me position dans les deux 
   * tableaux passés en param?tre.
   */
  public static int nombreDeValeurALaMemePlace(int[] t1, int t2[]) {
    int longueur = Math.min(t1.length, t2.length);
    int nb = 0;
    for(int i = 0; i < longueur; i++) {
      if (t1[i] == t2[i]) {
        nb++;
      }
    }
    return 0;
  }
}
