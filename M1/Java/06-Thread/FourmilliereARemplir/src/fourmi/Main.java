package fourmi;

/**
 *
 * @author maillot
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Fourmilliere("Fourmilliere");
            }
        });
    }

}
