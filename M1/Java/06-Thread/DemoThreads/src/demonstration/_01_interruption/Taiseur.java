package demonstration._01_interruption;

/**
 * Pour faire taire les bavards. Ou plus généralement, envoyer une interruption
 * à un thread au bout d'un temps donné
 *
 * @author yvan
 */
public class Taiseur extends Thread {

    private final Thread thread;
    private final int attente;

    public Taiseur(Thread thread, int attente) {
        this.thread = thread;
        this.attente = attente;
        start();
    }
    
    
    
    
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
/*
    @Override
    public void run() {
        try {
            sleep(attente);
            thread.interrupt();
            System.err.print("Chuuuut !!! à celui qui dit (" + thread + ") ");
        } catch (InterruptedException ie) {
        }
    }/*/
}
