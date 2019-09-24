
package salle;

/**
 *
 * @author maillot
 */
public class Table {
    private Philosophe[] philosophe;
    private Baguette[] baguette;
    public Table(int n) throws Exception {
        if (n < 2) throw new Exception("Il devrait y avoir au moins deux philosophes");
        philosophe = new Philosophe[n];
        baguette = new Baguette[n];
        for(int i = 0; i < philosophe.length; ++i) {
            philosophe[i] = new Philosophe(i+1);
            baguette[i] = new Baguette();
        }
        
        for(int i = 0; i < philosophe.length; ++i) {
            philosophe[i].setGauche(baguette[i]);
            baguette[i].setDroite(philosophe[i]);
            philosophe[i].setDroite(baguette[(i+1)%philosophe.length]);
            baguette[(i+1)%philosophe.length].setGauche(philosophe[i]);
        }
        
        for(int i = 0; i < philosophe.length; ++i) {
            philosophe[i].start();
        }
    }
    
    public Philosophe get(int i) {
        return philosophe[i];
    }
    
    public static void main(String[] args) throws Exception {
        new Table(2);
    }
}
