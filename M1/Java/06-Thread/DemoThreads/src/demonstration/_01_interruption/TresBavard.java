package demonstration._01_interruption;

/**
 * Une classe TresBavard parle trop
 *
 * @author yvan
 */
public class TresBavard extends Thread {

    private final String mot;

    /**
     * Un bavard répète mot sans arrêt
     *
     * @param mot le mot rabâché
     */
    public TresBavard(String mot) {
        this.mot = mot;
        start();
    }

    @Override
    public String toString() {
        return mot + "";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    /* (2) 
    @Override
    public void run() {
        boolean encore = true;
        while (encore) {
            if (Thread.interrupted()) {
                encore = false;
            }
            System.out.print(mot + " ");
        }
    }
    /**/
}
