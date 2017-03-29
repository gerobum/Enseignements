package exercices.boutons.interruption;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JButton;

/**
 * Modification du bouton pour que le bouton s'arr�te (resp. r�d�marre) quand on
 * clique s'il �tait en marche (resp. arr�t�).
 *
 * Par ailleur, on met fin d�finitevement au clignotement si la touche alt est
 * enfonc�e au moment du clic.
 */
public class BoutonClignotantAvecArretEtDepart extends JButton {

    private final Color on, off;
    private Clignoteur clignoteur;
    private boolean stop = false;


    /*
   * Ce thread fait clignoter le bouton.
   * Mais un boolean "stop" arr�te temporairement le clignotement.
   * Quand stop est vrai, le thread est mis en attente et le bouton ne clignote 
   * plus.
   * Quand stop est faux, le bouton clignote.
   * 
   * Par ailleurs, une interruption met d�finitivement fin au clignotement.
     */
    private class Clignoteur extends Thread {

        @Override
        public void run() {
            boolean fini = false;
            while (!fini) {
                try {
                    if (getBackground() == on) {
                        setBackground(off);
                        Thread.sleep(1000);
                    } else {
                        setBackground(on);
                        Thread.sleep(500);
                    }
                    // Pour mettre en attente le thread
                    while (stop) {
                        synchronized (this) { // Le r�veil devra se faire depuis le m�me bloc synchronis�.
                            setBackground(off);
                            wait();
                        }
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Quelqu'un a interrompu le clignotement");

                    fini = true;
                }
            }
            setBackground(off);
            System.out.println("Sortie du clignoteur");
        }
    }

    public BoutonClignotantAvecArretEtDepart(String texte, Color on, Color off) {
        super(texte);
        this.on = on;
        this.off = off;
        initUI();
    }

    private void initUI() {
        setBackground(on);
        setFont(getFont().deriveFont(50f));

        clignoteur = new Clignoteur();
        clignoteur.start();

        /// Ajout d'un actionListener qui interrompt le thread quand on clique
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Une interruption est envoy�e si le clic est fait alors que shift est 
                // enfonc�
                if ((e.getModifiers() ^ InputEvent.BUTTON1_MASK) == InputEvent.SHIFT_MASK) {
                    clignoteur.interrupt();
                    removeActionListener(this);
                } else {
                    // Sinon, un clic "normal" inverse le boole�n stop
                    // et r�veille le thread en attente.
                    synchronized (clignoteur) {
                        stop = !stop;
                        if (!stop) {
                            clignoteur.notify();
                        }
                    }
                }
            }
        });
    }
}
