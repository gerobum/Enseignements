package demonstration._01_interruption;

/**
 *
 * @author yvan
 */
/**
 * Une classe Bavard parle beaucoup
 *
 * @author yvan
 */
public class Bavard extends Thread {

    private final String mot;
    private final int ds;

    /**
     * Un bavard répète mot toutes les ds millisecondes
     *
     * @param mot le mot rabâché
     * @param ds la durée du silence
     */
    public Bavard(String mot, int ds) {
        this.mot = mot;
        this.ds = ds;
        start();
    }

    @Override
    public String toString() {
        return mot + "";
    }
    
    
    
    
    
    
    
    

 /* (1) 
    @Override
    public void run() {
        while (true) {
            try {
                System.out.print(mot + " ");
                sleep(ds);
            } catch (InterruptedException ie) {
            }
        }
    } */

    /* (2) 
    @Override
    public void run() {
        boolean encore = true;
        while (encore) {
            try {
                System.out.print(mot + " ");
                sleep(ds);
            } catch (InterruptedException ie) {
                encore = false;
            }
        }
    }
    /**/
}
