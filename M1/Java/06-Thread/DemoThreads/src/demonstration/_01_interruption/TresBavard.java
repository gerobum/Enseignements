package demonstration._01_interruption;

/**
 * Une classe TresBavard parle trop
 *
 * @author yvan
 */
public class TresBavard extends Thread {

    private final String mot;

    /**
     * Un bavard r�p�te mot sans arr�t
     *
     * @param mot le mot rab�ch�
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
