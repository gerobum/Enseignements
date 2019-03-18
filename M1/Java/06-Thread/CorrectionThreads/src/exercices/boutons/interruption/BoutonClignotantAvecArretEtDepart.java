package exercices.boutons.interruption;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JButton;

/**
 * Modification du bouton pour que le bouton s'arrête (resp. rédémarre) quand on
 * clique s'il était en marche (resp. arrêté).
 *
 * Par ailleur, on met fin définitevement au clignotement si la touche alt est
 * enfoncée au moment du clic.
 */
public class BoutonClignotantAvecArretEtDepart extends JButton {

    private final Color on, off;
    private Clignoteur clignoteur;
    private boolean stop = false;


    /*
   * Ce thread fait clignoter le bouton.
   * Mais un boolean "stop" arrête temporairement le clignotement.
   * Quand stop est vrai, le thread est mis en attente et le bouton ne clignote 
   * plus.
   * Quand stop est faux, le bouton clignote.
   * 
   * Par ailleurs, une interruption met définitivement fin au clignotement.
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
                        synchronized (this) { // Le réveil devra se faire depuis le même bloc synchronisé.
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
                // Une interruption est envoyée si le clic est fait alors que 
                // ALT est enfoncé
                // https://docs.oracle.com/javase/8/docs/api/constant-values.html#java.awt.event.InputEvent.BUTTON1_DOWN_MASK
                if ((e.getModifiers() & InputEvent.ALT_GRAPH_MASK) == InputEvent.ALT_GRAPH_MASK) {
                    
                    System.out.println("Bouton ALT enfoncé");
                    clignoteur.interrupt();
                    removeActionListener(this);
                } else {
                    // Sinon, un clic "normal" inverse le booléen stop
                    // et réveille le thread en attente.
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
