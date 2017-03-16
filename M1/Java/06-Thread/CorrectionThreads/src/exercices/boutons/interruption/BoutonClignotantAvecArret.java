package exercices.boutons.interruption;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class BoutonClignotantAvecArret extends JButton {

  private Color allume, eteint;
  private Clignoteur clignoteur;
  /*
   * Ce thread fait clignoter le bouton.
   * L'arr�t du clignotement se fait gr�ce � une interruption.
   */
  private class Clignoteur extends Thread {

    @Override
    public synchronized void run() {
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
        } catch (InterruptedException ex) {
          // La r�ception d'une interruption signifie que le clignotement
          // doit �tre s'arr�ter. Le bool�en fini est rendu vrai.
          // pour faire se terminer la bouche while.
          System.out.println("Quelqu'un a interrompu le clignotement");
          fini = true;
        }
      }
      // A la sortie de la boucle, le bouton s'�teint d�finivitement.
      setBackground(eteint);
      System.out.println("Sortie du clignoteur");
    }
  }

  public BoutonClignotantAvecArret(String texte, Color allum�, Color eteint) {
    super(texte);
    this.allume = allum�;
    this.eteint = eteint;
    setBackground(allum�);
    setFont(getFont().deriveFont(50f));

    clignoteur = new Clignoteur();
    clignoteur.start();

    /// Ajout d'un actionListener qui interrompt le thread quand on clique
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        /*
         * L'appui sur le bouton envoie une interruption au thread qui le fait
         * clignoter afin qu'il s'�teigne d�finitivement.
         */
        clignoteur.interrupt();
        removeActionListener(this);
      }
    });
  }
}
