package exercices.boutons.interruption;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 * Modification du bouton pour que le bouton s'arrête (resp. rédémarre) quand on
 * clique s'il était en marche (resp. arrêté).
 *
 * Par ailleur, on met fin définitevement au clignotement si la touche alt est
 * enfoncée au moment du clic.
 */
public class BoutonClignotantAvecArretEtDepart extends JButton {

  private Color allume, eteint;
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
          if (getBackground() == allume) {
            setBackground(eteint);
            Thread.sleep(1000);
          } else {
            setBackground(allume);
            Thread.sleep(500);
          }
          // Pour mettre en attente le thread
          while (stop) {
            synchronized (clignoteur) { // Le réveil devra se faire depuis le même bloc synchronisé.
              setBackground(eteint);
              wait();
            }
          }
        } catch (InterruptedException ex) {
          System.out.println("Quelqu'un a interrompu le clignotement");

          fini = true;
        }
      }
      setBackground(eteint);
      System.out.println("Sortie du clignoteur");
    }
  }

  public BoutonClignotantAvecArretEtDepart(String texte, Color allumé, Color eteint) {
    super(texte);
    this.allume = allumé;
    this.eteint = eteint;
    setBackground(allumé);
    setFont(getFont().deriveFont(50f));

    clignoteur = new Clignoteur();
    clignoteur.start();

    /// Ajout d'un actionListener qui interrompt le thread quand on clique
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(e.getModifiers() + " : " + ActionEvent.SHIFT_MASK);
        // Une interruption est envoyée si le clic est fait alors que shift est 
        // enfoncé
        if ((e.getModifiers() ^ InputEvent.BUTTON1_MASK) == InputEvent.SHIFT_MASK) {
          clignoteur.interrupt();
          removeActionListener(this);
        } else {
          // Sinon, un clic "normal" inverse le booleén stop
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
